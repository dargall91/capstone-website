import React, { useContext, useEffect, useState } from "react";
import axios from "axios";

const baseUrl = "http://localhost:8080/wea/api/";

const AlertContext = React.createContext();

const AlertProvider = ({ children }) => {
  // State Variables
  const [showModal, setShowModal] = useState(false);
  const [selectedAlert, setSelectedAlert] = useState(null);
  const [dbAlertList, setDbAlertList] = useState([]);
  const [fullData, setFullData] = useState([]);
  const [modalImage, setModalImage] = useState("");
  const [alertOriginator, setAlertOriginator] = useState("");
  const [date, setDate] = useState("");
  const [login, setLogin] = useState(false);
  const [page, setPage] = useState(1);
  const [filters, setFilters] = useState("");

  // Functions
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

  const getDate = () => {
    let today = new Date();
    const formatted = new Intl.DateTimeFormat("en-us", {
      dateStyle: "long",
      timeStyle: "short",
    });
    setDate(formatted.format(today));
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
    console.log(result.data);
    return result.data;
  };

  useEffect(() => {
    if (!alertOriginator) {
      return;
    }

    getDate();

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
    <AlertContext.Provider
      value={{
        showModal,
        dbAlertList,
        selectAlert,
        selectedAlert,
        closeModal,
        fullData,
        modalImage,
        setModalImage,
        setAlertOriginator,
        getMessageList,
        login,
        setLogin,
        setPage,
        buildFilters,
        date,
        increasePage,
        decreasePage,
      }}
    >
      {children}
    </AlertContext.Provider>
  );
};

export const useAlertContext = () => {
  return useContext(AlertContext);
};

export { AlertContext, AlertProvider };
