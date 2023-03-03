import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import { useAlertContext } from "./AlertContext";

const LoginContext = React.createContext();

const LoginProvider = ({ children }) => {
  // State Variables

  const [alertOriginator, setAlertOriginator] = useState("");
  const [login, setLogin] = useState(false);
  const [date, setDate] = useState("");

  // Functions

  const getDate = () => {
    let today = new Date();
    const formatted = new Intl.DateTimeFormat("en-us", {
      dateStyle: "long",
      timeStyle: "short",
    });
    setDate(formatted.format(today));
  };

  // useEffects

  return (
    <LoginContext.Provider
      value={{
        alertOriginator,
        setAlertOriginator,
        login,
        setLogin,
        date,
        getDate,
      }}
    >
      {children}
    </LoginContext.Provider>
  );
};

export const useLoginContext = () => {
  return useContext(LoginContext);
};

export { LoginContext, LoginProvider };
