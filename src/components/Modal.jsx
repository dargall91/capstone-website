import { useState, useRef } from "react";
import { useAlertContext } from "../AlertContext";

const Card = ({ frontContentText, frontContentValue, backContent }) => {
  const [isFlipped, setIsFlipped] = useState(false);

  return (
    <div
      className={`card ${isFlipped ? "isFlipped" : ""}`}
      onClick={() => {
        setIsFlipped(!isFlipped);
      }}
    >
      <div className="front">
        <div className="front-data-header">{frontContentText}</div>
        <div className="seperator"></div>
        <div>{frontContentValue}</div>
      </div>

      <div className="back">{backContent}</div>
    </div>
  );
};

const Modal = () => {
  const { selectedAlert, closeModal, modalImage } = useAlertContext();

  const {
    sentDateTime,
    expiresDateTime,
    messageNumber,
    averageTime,
    shortestTime,
    averageDisplayDelay,
    deviceCount,
    receivedOutside,
    displayedOutside,
    firstDisplayed,
    firstReceived,
    averageDistanceFromPolygon,
    expectedDeviceCount,
  } = selectedAlert[0];

  const handleClick = (e) => {
    e.stopPropagation();
  };

  return (
    <aside className="modal-overlay" onClick={closeModal}>
      <div className="modal-container" onClick={handleClick}>
        <header>
          <h4>Message Number: {messageNumber}</h4>
          <h4>Time Sent: {sentDateTime}</h4>
          <h4>Time Expired: {expiresDateTime}</h4>
        </header>
        <img className="img modal-img" src={modalImage} />

        <div className="modal-content">
          <div className="card-grid">
            <Card
              frontContentText={`Average Time`}
              frontContentValue={averageTime === null ? "N/A" : averageTime}
              backContent="This is some test back content"
            ></Card>

            <Card
              frontContentText={`Lowest Response Time`}
              frontContentValue={shortestTime === null ? "N/A" : shortestTime}
              backContent="Lowest response time test content"
            ></Card>

            <Card
              frontContentText={`Average Time Delay`}
              frontContentValue={
                averageDisplayDelay === null
                  ? " N/A"
                  : ` ${averageDisplayDelay}`
              }
              backContent="This is some back content that is supposed to be long so that is has the opportunity to show off how much space we are able to utilize for expanded explanation."
            ></Card>

            <Card
              frontContentText={`Device Count`}
              frontContentValue={
                deviceCount === null ? " N/A" : ` ${deviceCount}`
              }
              backContent="Back"
            ></Card>

            <Card
              frontContentText={`Total devices received outside area`}
              frontContentValue={
                receivedOutside === null ? " N/A" : ` ${receivedOutside}`
              }
              backContent="Back"
            ></Card>

            <Card
              frontContentText={`Total devices displayed outside area`}
              frontContentValue={
                displayedOutside === null ? " N/A" : ` ${displayedOutside}`
              }
              backContent="Back"
            ></Card>

            <Card
              frontContentText={`First displayed time`}
              frontContentValue={
                firstDisplayed === null ? " N/A" : ` ${firstDisplayed}`
              }
              backContent="Back"
            ></Card>

            <Card
              frontContentText={`First received time`}
              frontContentValue={
                firstReceived === null ? " N/A" : ` ${firstReceived}`
              }
              backContent="Back"
            ></Card>

            <Card
              frontContentText={`Average distance outside of Alert Area`}
              frontContentValue={
                averageDistanceFromPolygon === null
                  ? " N/A"
                  : ` ${averageDistanceFromPolygon}`
              }
              backContent="Back"
            ></Card>

            <Card
              frontContentText={`Expected total device count`}
              frontContentValue={
                expectedDeviceCount === null
                  ? " N/A"
                  : ` ${expectedDeviceCount}`
              }
              backContent="Back"
            ></Card>
          </div>
        </div>
      </div>
    </aside>
  );
};

export default Modal;
