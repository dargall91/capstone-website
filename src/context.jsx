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
  const selectAlert = (idAlert) => {
    let alert;

    alert = dbAlertList.filter((alert) => alert.messageNumber === idAlert);
    setSelectedAlert(alert);
    setShowModal(true);
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

  const getCoords = (coordList) => {
    let coordPairs = coordList.split(" ");

    let x = [];
    let y = [];

    coordPairs.array.forEach((element) => {
      let splitCoords = coordPairs.split(",");
      x.push(splitCoords[0]);
      y.push(splitCoords[1]);
    });

    let finalCoords = {
      x: x,
      y: y,
    };

    return finalCoords;
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
        getCoords,
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
