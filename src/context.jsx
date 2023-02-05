import React, { useContext, useEffect, useState } from "react";
import axios from "axios";

// API KEY: AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g

// any static variables go here
const monthHolder = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];
const baseUrl = "http://localhost:8080/wea/api/";

const AppContext = React.createContext();

// Majority of logic takes place inside of the AppProvider function
const AppProvider = ({ children }) => {
  // State Variables

  const [date, setDate] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [selectedAlert, setSelectedAlert] = useState(null);
  const [alertOriginator, setAlertOriginator] = useState("");
  const [login, setLogin] = useState(false);
  const [dbAlertList, setDbAlertList] = useState([]);
  const [fullData, setFullData] = useState([]);
  const [page, setPage] = useState(1);
  const [filters, setFilters] = useState("");
  const [modalImage, setModalImage] = useState("");

  // Functions
  const getDate = () => {
    let today = new Date();
    let day = String(today.getDate());
    let month = monthHolder[today.getMonth()];
    let year = today.getFullYear();
    setDate(`${month} ${day}, ${year}`);
  };

  /**
   * Filters the array of messages to locate the id of the selected
   * alert so that the modal can perform its operations.
   *
   * @param {int} idAlert
   */
  const selectAlert = (idAlert, source) => {
    let alert;

    alert = dbAlertList.filter((alert) => alert.messageNumber === idAlert);
    setSelectedAlert(alert);
    setShowModal(true);
    setModalImage(source);
  };

  const closeModal = () => {
    if (dbAlertList.length === 0) {
      return;
    }
    setShowModal(false);
  };

  const increasePage = () => {
    if (fullData.next) {
      let curr = page;
      curr = curr + 1;
      setPage(curr);
    }
  };

  const decreasePage = () => {
    if (fullData.prev) {
      let curr = page;
      curr = curr - 1;
      setPage(curr);
    }
  };

  const getCenter = (coords) => {
    let minX, maxX, minY, maxY;

    coords.map((idx) => {
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
    coordinates.map((idx) => {
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

    geocodes.map((idx) => {
      src += `${idx}`;
      if (pathIndex !== length - 1) {
        src += `|`;
      }
      pathIndex++;
    });

    console.log(src);

    return src;
  };

  const buildFilters = ({ mType, mNum, frDate, toDate, sortBy, sortOrder }) => {
    let filterString = `?${mType !== "" ? "messageType=" : ""}${mType}${
      mType !== "" ? "&" : ""
    }${mNum !== "" ? "messageNumber=" : ""}${mNum}${mNum !== "" ? "&" : ""}${
      frDate !== "" ? "fromDate=" : ""
    }${frDate}${frDate !== "" ? "&" : ""}${
      toDate !== "" ? "toDate=" : ""
    }${toDate}${
      toDate !== "" ? "&" : ""
    }sortBy=${sortBy}&sortOrder=${sortOrder}`;

    setFilters(filterString);
  };

  /**
   * Fetches a list of cmac_message_numbers and their cmac_sent_date_times
   * that belong to a specified AO from the server. Call this function
   * when the user logs in to get their list of messages and use the list
   * to create the cards
   *
   * @param {string} ao The AO who's messages should be fetched.
   *                    '@' characters must be encoded as "%40"
   */
  const getMessageList = async (ao) => {
    const result = await axios(
      `${baseUrl}${ao}/messages/${page}/filter${filters}`
    );
    return result.data;
  };

  // useEffects
  useEffect(() => {
    getDate();
  });

  useEffect(() => {
    if (!alertOriginator) {
      return;
    }

    getMessageList(alertOriginator).then((data) =>
      setDbAlertList(data.messageStats)
    );

    getMessageList(alertOriginator).then((data) => setFullData(data));
    // eslint-disable-next-line
  }, [alertOriginator]);

  useEffect(() => {
    if (!alertOriginator) {
      return;
    }
    getMessageList(alertOriginator).then((data) =>
      setDbAlertList(data.messageStats)
    );

    getMessageList(alertOriginator).then((data) => setFullData(data));
    // eslint-disable-next-line
  }, [page]);

  useEffect(() => {
    if (!alertOriginator) {
      return;
    }
    getMessageList(alertOriginator).then((data) =>
      setDbAlertList(data.messageStats)
    );

    getMessageList(alertOriginator).then((data) => setFullData(data));
    // eslint-disable-next-line
  }, [filters]);

  return (
    <AppContext.Provider
      value={{
        date,
        dbAlertList,
        showModal,
        selectAlert,
        selectedAlert,
        closeModal,
        setAlertOriginator,
        login,
        setLogin,
        page,
        setPage,
        increasePage,
        decreasePage,
        fullData,
        buildFilters,
        modalImage,
        setModalImage,
        buildURL,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export const useGlobalContext = () => {
  return useContext(AppContext);
};

export { AppContext, AppProvider };
