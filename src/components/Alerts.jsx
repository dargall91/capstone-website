import { useGlobalContext } from "../context";

const Alerts = () => {
  const { selectAlert, dbAlertList, page } = useGlobalContext();

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
      {dbAlertList.map((alert) => {
        const { messageNumber, date, messageType } = alert;
        const source =
          "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=300x150&maptype=roadmap&key=AIzaSyB0Zq3fWV9fXL-_v3A5DGIZXXMnu89A60g";
        return (
          <article key={messageNumber} className="single-alert">
            <header>
              <h5>{date}</h5>
              <h5>{messageNumber}</h5>
            </header>

            <img
              className="img"
              src={source}
              onClick={() => selectAlert(messageNumber, source)}
            />
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
