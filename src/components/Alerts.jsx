import { useAlertContext } from "../AlertContext";
import { usePolygonContext } from "../PolygonContext";

const Alerts = () => {
  const { selectAlert, dbAlertList, page } = useAlertContext();
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
      {dbAlertList.map((alert) => {
        const {
          messageNumber,
          sentDateTime,
          messageType,
          coordinates,
          areaNames,
        } = alert;

        let location = {
          coordinates: coordinates,
          geocodes: areaNames,
        };

        const source = buildURL(location);

        // const source = "";

        return (
          <article key={messageNumber} className="single-alert">
            <header>
              <h5>{sentDateTime}</h5>
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
