import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { useAlertContext } from "./AlertContext";

// API KEY: AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g

const NavigationContext = React.createContext();

// Majority of logic takes place inside of the AppProvider function
const NavigationProvider = ({ children }) => {
  // State Variables

  const [page, setPage] = useState(1);
  const [filters, setFilters] = useState("");

  // Functions

  const increasePage = () => {
    const { fullData } = useAlertContext();
    if (fullData.next) {
      let curr = page;
      curr = curr + 1;
      setPage(curr);
    }
  };

  const decreasePage = () => {
    const { fullData } = useAlertContext();
    if (fullData.prev) {
      let curr = page;
      curr = curr - 1;
      setPage(curr);
    }
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

  return (
    <NavigationContext.Provider
      value={{
        page,
        setPage,
        increasePage,
        decreasePage,
        buildFilters,
        filters,
      }}
    >
      {children}
    </NavigationContext.Provider>
  );
};

export const useNavigationContext = () => {
  return useContext(NavigationContext);
};

export { NavigationContext, NavigationProvider };
