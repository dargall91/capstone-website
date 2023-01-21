import { useGlobalContext } from "../context";

const Alerts = () => {
  const { selectAlert, dbAlertList, page, getCoords } = useGlobalContext();

  if (dbAlertList.length < 1 && page !== 1) {
    return (
      <section>
        <h4>No more results.</h4>
      </section>
    );
  }

  if (dbAlertList.length < 1) {
    return (
      <section>
        <h4>No alerts have been retrieved.</h4>
      </section>
    );
  }

  return (
    <section className="section-center">
      <img src="https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=300x150&maptype=roadmap&key=AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g" />
      {dbAlertList.map((alert) => {
        const { messageNumber, date, messageType } = alert;
        // const { x, y } = getCoords(coordinates);
        return (
          <article key={messageNumber} className="single-alert">
            <header>
              <h5>{date}</h5>
              <h5>{messageNumber}</h5>
            </header>

            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="300"
              height="150"
              viewBox="0 0 300 150"
              className="img"
              onClick={() => selectAlert(messageNumber)}
            >
              <rect fill="#ddd" width="300" height="150" />
              <text
                fill="rgba(0,0,0,0.5)"
                fontFamily="sans-serif"
                fontSize="30"
                dy="10.5"
                fontWeight="bold"
                x="50%"
                y="50%"
                textAnchor="middle"
              >
                300×150
              </text>
            </svg>
            <footer>
              <h5 className="message-type">{messageType}</h5>
            </footer>
          </article>
        );
      })}
    </section>
  );
};

export default Alerts;
