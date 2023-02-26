import { useAlertContext } from "../AlertContext";
import { useGlobalContext } from "../context";
import { usePolygonContext } from "../PolygonContext";

const Alerts = () => {
  const { page } = useGlobalContext();
  const { selectAlert, dbAlertList } = useAlertContext();
  const { buildURL } = usePolygonContext();

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
      {dbAlertList.forEach((alert) => {
        const { messageNumber, date, messageType, coordinates, geocodes } =
          alert;

        let location = {
          coordinates: coordinates,
          geocodes: geocodes,
        };

        const source = buildURL(location);

        // const source = "";

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
