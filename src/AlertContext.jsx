import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { useGlobalContext } from "./context";

const baseUrl = "http://localhost:8080/wea/api/";

const AlertContext = React.createContext();

const AlertProvider = ({ children }) => {
  // State Variables
  const [showModal, setShowModal] = useState(false);
  const [selectedAlert, setSelectedAlert] = useState(null);
  const [dbAlertList, setDbAlertList] = useState([]);
  const [fullData, setFullData] = useState([]);
  const [modalImage, setModalImage] = useState("");

  const { alertOriginator, page, filters } = useGlobalContext();

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
