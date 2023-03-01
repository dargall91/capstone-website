import React from "react";
import ReactDOM from "react-dom/client";
import { AlertProvider } from "./AlertContext";
import App from "./App";
import { NavigationProvider } from "./NavigationContext";
import { LoginProvider } from "./LoginContext";
import { PolygonProvider } from "./PolygonContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <LoginProvider>
      <NavigationProvider>
        <AlertProvider>
          <PolygonProvider>
            <App />
          </PolygonProvider>
        </AlertProvider>
      </NavigationProvider>
    </LoginProvider>
  </React.StrictMode>
);
