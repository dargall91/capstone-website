import { useAlertContext } from "../AlertContext";

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
    e.stopImmediatePropagation();
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
          <p>Average Time: {averageTime === null ? "N/A" : averageTime}</p>
          <p>
            Lowest Response Time: {shortestTime === null ? "N/A" : shortestTime}
          </p>
          <p>
            Average Time Delay:
            {averageDisplayDelay === null ? " N/A" : ` ${averageDisplayDelay}`}
          </p>
          <p>
            Device Count: {deviceCount === null ? " N/A" : ` ${deviceCount}`}
          </p>
          <p>
            Total devices receieved outside area:
            {receivedOutside === null ? " N/A" : ` ${receivedOutside}`}
          </p>
          <p>
            Total devices displayed outside area:
            {displayedOutside === null ? " N/A" : ` ${displayedOutside}`}
          </p>
          <p>
            First displayed time:
            {firstDisplayed === null ? " N/A" : ` ${firstDisplayed}`}
          </p>
          <p>
            First receieved time:
            {firstReceived === null ? " N/A" : ` ${firstReceived}`}
          </p>
          <p>
            Average distance outside of Alert Area:
            {averageDistanceFromPolygon === null
              ? " N/A"
              : ` ${averageDistanceFromPolygon}`}
          </p>
          <p>
            First receieved time:
            {expectedDeviceCount === null ? " N/A" : ` ${expectedDeviceCount}`}
          </p>
        </div>
      </div>
    </aside>
  );
};

export default Modal;
