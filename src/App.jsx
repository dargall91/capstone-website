import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Alerts from "./components/Alerts";
import Modal from "./components/Modal";
import Login from "./components/Login";
import NavOptions from "./components/NavOptions";
import Filters from "./components/Filters";
import MapContainer from "./components/MapContainer";
import { useAlertContext } from "./AlertContext";
import { useLoginContext } from "./LoginContext";

// I placed test text and context placeholder text for
// everyone to hopefully understand how the context works.
// It will show up in the test text main, and also the developer console.

function App() {
  const { login } = useLoginContext();
  const { showModal } = useAlertContext();

  return (
    <main>
      <div className="page-content">
        <div className="content-wrap">
          {!login && <Login></Login>}
          {login && <Header></Header>}
          {login && <Filters></Filters>}
          {login && <NavOptions></NavOptions>}
          {/* {login && <MapContainer></MapContainer>} */}
          {login && <Alerts></Alerts>}
          {showModal && <Modal></Modal>}
        </div>
        {login && <Footer></Footer>}
      </div>
    </main>
  );
}

export default App;
