import React from "react";
import { GoogleMap, LoadScript } from "@react-google-maps/api";

const MapContainer = () => {
  const mapStyles = {
    height: "150px",
    width: "300px",
  };
  const defaultCenter = {
    lat: 41.3851,
    lng: 2.1734,
  };
  return (
    <LoadScript googleMapsApiKey="AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g">
      <GoogleMap
        mapContainerStyle={mapStyles}
        zoom={12}
        center={defaultCenter}
      ></GoogleMap>
    </LoadScript>
  );
};

export default MapContainer;
