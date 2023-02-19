import React, { useContext, useEffect, useState } from "react";
import axios from "axios";

const PolygonContext = React.createContext();

// Majority of logic takes place inside of the AppProvider function
const PolygonProvider = ({ children }) => {
  // State Variables

  const [test, setTest] = useState(false);

  // Functions
  const getCenter = (coords) => {
    let minX, maxX, minY, maxY;

    coords.forEach((idx) => {
      minX = Number(idx.lat) < minX || minX == null ? Number(idx.lat) : minX;
      maxX = Number(idx.lat) > maxX || maxX == null ? Number(idx.lat) : maxX;
      minY = Number(idx.lon) < minY || minY == null ? Number(idx.lon) : minY;
      maxY = Number(idx.lon) > maxY || maxY == null ? Number(idx.lon) : maxY;
    });

    return [(minX + maxX) / 2, (minY + maxY) / 2];
  };

  const getGeocodeCenter = async (geo) => {
    let codes = [`fisher+tx`, `abilene+tx`, `houston+tx`];

    const container = [];

    codes.forEach(async (idx) => {
      let url = `https://maps.googleapis.com/maps/api/geocode/json?address=${idx}&key=AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g`;

      const result = await axios(url);

      let item = {
        lat: result.data.results[0].geometry.location.lat,
        lon: result.data.results[0].geometry.location.lng,
      };
      container.push(item);
    });

    let center = [];
    center = await getCenter(container);
    console.log(center);
  };

  const buildURL = ({ coordinates, geocodes }) => {
    // Initial source url
    let src = `https://maps.googleapis.com/maps/api/staticmap?center=`;

    if (coordinates !== null) {
      src += coordinatesBuilder(coordinates);
    } else {
      src += geocodesBuilder(geocodes);
    }

    // Finalize the URL
    src += `&size=300x150&maptype=roadmap&key=AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g`;

    return src;
  };

  const coordinatesBuilder = (coordinates) => {
    let src = ``;

    let center = [];
    center = getCenter(coordinates);
    src += `${center[0]},${center[1]}&`;

    // Attach the coordinates
    let length = Object.keys(coordinates).length;

    src += `path=weight:4|color:red|fillcolor:red|`;

    let pathIndex = 0;
    coordinates.forEach((idx) => {
      src += `${idx.lat},${idx.lon}`;
      if (pathIndex !== length - 1) {
        src += `|`;
      }
      pathIndex++;
    });
    console.log(src);

    return src;
  };

  const geocodesBuilder = (geocodes) => {
    let src = ``;

    let length = Object.keys(geocodes).length;

    // Since geocodes do not contain coordinates, we are letting
    // the map API viewport decide where the center of the map is

    src += `&path=weight:4|color:red|fillcolor:red|`;

    let pathIndex = 0;

    // geocodes.array.forEach((idx) => {
    //   src += `${geocodes[idx]}`;
    //   if (idx !== geocodes.length - 1) {
    //     src += `|`;
    //   }
    // });

    geocodes.forEach((idx) => {
      src += `${idx}`;
      if (pathIndex !== length - 1) {
        src += `|`;
      }
      pathIndex++;
    });

    console.log(src);

    return src;
  };

  return (
    <PolygonContext.Provider
      value={{
        buildURL,
      }}
    >
      {children}
    </PolygonContext.Provider>
  );
};

export const usePolygonContext = () => {
  return useContext(PolygonContext);
};

export { PolygonContext, PolygonProvider };
