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
    messageNumber,
    sentDateTime,
    expiresDateTime,
    averageTime,
    shortestTime,
    firstReceived,
    averagePresentationDelay,
    firstPresented,
    received,
    expectedReceived,
    receivedOutside,
    presented,
    notPresentedOutside,
    presentedDefault,
    optedOut,
    averageDistance,
    medianDistance,
    minDistance,
    maxDistance,
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
          <div className="card-section-header">Accuracy</div>
          <div className="card-grid">
            <Card
              frontContentText={`Average Distance`}
              frontContentValue={
                averageDistance === null ? " N/A" : ` ${averageDistance}`
              }
              backContent="The average distance outside of the target area for all devices that received the message outside the target area in miles"
            ></Card>

            <Card
              frontContentText={`Median Distance`}
              frontContentValue={
                medianDistance === null ? " N/A" : ` ${medianDistance}`
              }
              backContent="The median distance outside of the target area for all devices that received the message outside the target area in miles"
            ></Card>

            <Card
              frontContentText={`Min Distance`}
              frontContentValue={
                minDistance === null ? " N/A" : ` ${minDistance}`
              }
              backContent="The minimum distance outside of the target area for all devices that received the message outside the target area in miles"
            ></Card>

            <Card
              frontContentText={`Max Distance`}
              frontContentValue={
                maxDistance === null ? " N/A" : ` ${maxDistance}`
              }
              backContent="The maximum distance outside of the target area for all devices that received the message outside the target area in miles"
            ></Card>
          </div>

          <div className="card-section-header">Reliability</div>
          <div className="card-grid">
            <Card
              frontContentText={`Expected Total Devices Received`}
              frontContentValue={
                expectedReceived === null ? " N/A" : ` ${expectedReceived}`
              }
              backContent="The number of devices that were expected to receive the message"
            ></Card>

            <Card
              frontContentText={`Actual Total Devices Received`}
              frontContentValue={received === null ? " N/A" : ` ${received}`}
              backContent="The number of devices that received the message"
            ></Card>

            <Card
              frontContentText={`Received Outside`}
              frontContentValue={
                receivedOutside === null ? " N/A" : ` ${receivedOutside}`
              }
              backContent="The number of devices that received the message outside of the target area"
            ></Card>

            <Card
              frontContentText={`Received Outside but Not Presented`}
              frontContentValue={
                notPresentedOutside === null
                  ? " N/A"
                  : ` ${notPresentedOutside}`
              }
              backContent="The number of devices that received the message outside of the target area but did not present it"
            ></Card>

            <Card
              frontContentText={`Total Presentations`}
              frontContentValue={presented === null ? " N/A" : ` ${presented}`}
              backContent="The number of devices that presented the message"
            ></Card>

            <Card
              frontContentText={`Default Presentation`}
              frontContentValue={
                presentedDefault === null ? " N/A" : ` ${presentedDefault}`
              }
              backContent="The number of devices that received the message and presented it by default for any reason (ex: device location not enabled)"
            ></Card>

            <Card
              frontContentText={`Opt Outs`}
              frontContentValue={optedOut === null ? " N/A" : ` ${optedOut}`}
              backContent="The number of devices that received the message but did not display it because the user opted out of that type of message"
            ></Card>
          </div>

          <div className="card-section-header">Latency</div>
          <div className="card-grid">
            <Card
              frontContentText={`Average Time`}
              frontContentValue={averageTime === null ? "N/A" : averageTime}
              backContent="The average time between when the message was issued and all devices received it"
            ></Card>

            <Card
              frontContentText={`All Device Received Time`}
              frontContentValue={shortestTime === null ? "N/A" : shortestTime}
              backContent="The shortest time between when the message was issued and all devices received it"
            ></Card>

            <Card
              frontContentText={`First received time`}
              frontContentValue={
                firstReceived === null ? " N/A" : ` ${firstReceived}`
              }
              backContent="The date and time when the first device to receive the message received it"
            ></Card>

            <Card
              frontContentText={`Average Presentation Delay`}
              frontContentValue={
                averagePresentationDelay === null
                  ? " N/A"
                  : ` ${averagePresentationDelay}`
              }
              backContent="The average time lapse between when all devices received the message and when the alert was presented on the device (excluding devices that did not receive the message)"
            ></Card>

            <Card
              frontContentText={`First Presentation`}
              frontContentValue={
                firstPresented === null ? " N/A" : ` ${firstPresented}`
              }
              backContent="The date and time when the first device to display the message presented it"
            ></Card>
          </div>
        </div>
      </div>
    </aside>
  );
};

export default Modal;
