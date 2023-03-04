import React from "react";
import ReactDOM from "react-dom/client";
import { AlertProvider } from "./AlertContext";
import App from "./App";
import { PolygonProvider } from "./PolygonContext";

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
