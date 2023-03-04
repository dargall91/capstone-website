import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { PolygonProvider } from "./PolygonContext";
import { AlertProvider } from "./AlertContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <AlertProvider>
      <PolygonProvider>
        <App />
      </PolygonProvider>
    </AlertProvider>
  </React.StrictMode>
);
