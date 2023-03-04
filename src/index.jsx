import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { PolygonProvider } from "./PolygonContext";
import { AlertProvider } from "./AlertContext";
import { NavigationProvider } from "./NavigationContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <AlertProvider>
      <NavigationProvider>
        <PolygonProvider>
          <App />
        </PolygonProvider>
      </NavigationProvider>
    </AlertProvider>
  </React.StrictMode>
);
