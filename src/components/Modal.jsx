import { useGlobalContext } from "../context";

const Modal = () => {
  const { selectedAlert, closeModal, modalImage } = useAlertContext();

  const {
    date,
    messageNumber,
    averageTime,
    shortestTime,
    longestTime,
    averageDelay,
    deviceCount,
    receivedOutsideCount,
    displayedOutsideCount,
    receivedAfterExpiredCount,
    displayedAfterExpiredCount,
  } = selectedAlert[0];

  return (
    <aside className="modal-overlay">
      <div className="modal-container">
        <header>
          <h4>CMAC Alert {messageNumber}</h4>
          <h4>{date}</h4>
          <button
            className="btn btn-hipster close-btn top-btn"
            onClick={closeModal}
          >
            Close
          </button>
        </header>
        <img className="img modal-img" src={modalImage} />

        <div className="modal-content">
          <p>CMAC Average Time: {averageTime}</p>
          <p>CMAC Lowest Response Time: {shortestTime}</p>
          <p>CMAC Highest Reponse Time: {longestTime}</p>
          <p>CMAC Average Time Delay: {averageDelay}</p>
          <p>Device Count: {deviceCount}</p>
          <p>Total devices receieved outside area: {receivedOutsideCount}</p>
          <p>Total devices receieved outside area: {displayedOutsideCount}</p>
          <p>
            Total devices received after expired: {receivedAfterExpiredCount}
          </p>
          <p>
            Total devices displayed after expired: {displayedAfterExpiredCount}
          </p>
          <button className="btn btn-hipster close-btn" onClick={closeModal}>
            Close
          </button>
        </div>
      </div>
    </aside>
  );
};

export default Modal;
