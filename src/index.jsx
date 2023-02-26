import React from "react";
import ReactDOM from "react-dom/client";
import { AlertProvider } from "./AlertContext";
import App from "./App";
import { AppProvider } from "./context";
import { PolygonProvider } from "./PolygonContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <PolygonProvider>
      <AppProvider>
        <AlertProvider>
          <App />
        </AlertProvider>
      </AppProvider>
    </PolygonProvider>
  </React.StrictMode>
);
