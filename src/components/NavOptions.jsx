import { useAlertContext } from "../AlertContext";
import { useNavigationContext } from "../NavigationContext";

const NavOptions = () => {
  const { increasePage, decreasePage, page } = useNavigationContext();
  const { fullData } = useAlertContext();

  const next = fullData.next;
  const prev = fullData.prev;

  return (
    <section className="nav-options">
      <div className="nav">
        <button
          className="nav-btn btn btn-hipster"
          onClick={decreasePage}
          style={{ visibility: prev ? "visible" : "hidden" }}
        >
          &lt;&lt; Prev
        </button>
        <div className="curr-page-container">
          <p className="curr-page">Current Page: {page}</p>
        </div>
        <button
          className="nav-btn btn btn-hipster"
          onClick={increasePage}
          style={{ visibility: next ? "visible" : "hidden" }}
        >
          Next &gt;&gt;
        </button>
      </div>
    </section>
  );
};

export default NavOptions;
