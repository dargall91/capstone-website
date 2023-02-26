import { useAlertContext } from "../AlertContext";
import { useGlobalContext } from "../context";

const Header = () => {
  const { date } = useGlobalContext();
  const { fullData } = useAlertContext();

  return (
    <header className="header-container">
      <a href="https://www.att.com/" target="_blank" rel="noreferrer">
        <svg
          className="logo"
          xmlns="http://www.w3.org/2000/svg"
          width="1000.265"
          height="411.08"
          viewBox="-0.082 -42.217 1000.265 411.08"
        >
          <g id="g3">
            <path
              d="M925.371,245.707c-2.676,0-4.519-1.855-4.519-4.523V108.655h-45.021c-2.677,0-4.523-1.847-4.523-4.518V85.856   c0-2.677,1.847-4.529,4.523-4.529h119.838c2.674,0,4.515,1.854,4.515,4.529v18.28c0,2.669-1.841,4.519-4.515,4.519h-45.018v132.527   c0,2.67-1.852,4.525-4.519,4.525H925.371 M580.503,179.541l-23.633-67.8l-23.845,67.8H580.503z M632.919,240.352   c1.033,2.678-0.615,5.355-3.493,5.355h-21.373c-3.085,0-4.937-1.434-5.968-4.324l-12.122-34.918h-66.386l-12.143,34.918   c-1.02,2.891-2.877,4.324-5.954,4.324h-20.137c-2.676,0-4.529-2.678-3.5-5.355l55.702-154.907c1.031-2.886,2.88-4.111,5.957-4.111   h27.544c3.086,0,5.142,1.226,6.169,4.111L632.919,240.352 M792.583,223.512c13.149,0,21.992-6.359,29.188-17.254l-33.293-35.756   c-12.75,7.197-20.976,14.379-20.976,28.766C767.504,213.448,779.009,223.512,792.583,223.512 M801.831,103.106   c-10.685,0-16.851,6.787-16.851,15.826c0,6.985,3.692,13.15,12.123,22.193c14.592-8.43,20.762-13.564,20.762-22.602   C817.865,110.092,812.523,103.106,801.831,103.106 M892.896,239.953c2.669,2.879,1.028,5.754-2.27,5.754h-26.104   c-3.493,0-5.343-0.826-7.603-3.5l-15.621-17.252c-10.481,13.973-25.087,24.449-49.336,24.449c-30.008,0-53.653-18.08-53.653-49.1   c0-23.842,12.751-36.584,32.075-47.266c-9.463-10.889-13.771-22.396-13.771-32.459c0-25.485,17.881-42.947,44.803-42.947   c27.544,0,44.402,16.238,44.402,40.273c0,20.547-14.796,32.043-30.423,40.679l23.025,24.87l12.947-22.61   c1.643-2.667,3.494-3.698,6.778-3.698h19.934c3.294,0,5.144,2.266,3.095,5.757l-23.026,39.444L892.896,239.953 M687.765,245.707   c2.672,0,4.531-1.855,4.531-4.523V108.655h45.008c2.672,0,4.52-1.847,4.52-4.518V85.856c0-2.677-1.848-4.529-4.52-4.529H617.466   c-2.676,0-4.522,1.854-4.522,4.529v18.28c0,2.669,1.847,4.519,4.522,4.519h45.008v132.527c0,2.67,1.856,4.525,4.525,4.525H687.765   L687.765,245.707z"
              id="path5"
            />
            <path
              fill="#00A8E0"
              d="M79.446,325.647c34.859,26.984,78.613,43.197,126.084,43.197c51.949,0,99.308-19.287,135.452-50.947   c0.438-0.387,0.222-0.643-0.21-0.387c-16.219,10.832-62.445,34.477-135.24,34.477c-63.262,0-103.241-14.115-125.818-26.717   C79.282,325.057,79.122,325.381,79.446,325.647 M219.487,336.153c50.598,0,106.199-13.793,139.453-41.096   c9.1-7.439,17.768-17.34,25.531-30.646c4.469-7.656,8.84-16.752,12.4-25.693c0.158-0.436-0.111-0.648-0.439-0.158   c-30.924,45.508-120.473,73.893-212.937,73.893c-65.357,0-135.68-20.9-163.212-60.807c-0.271-0.369-0.542-0.211-0.377,0.213   C45.554,306.373,123.364,336.153,219.487,336.153 M164.204,245.717c-105.234,0-154.854-49.012-163.855-82.459   c-0.111-0.484-0.43-0.378-0.43,0.057c0,11.26,1.127,25.791,3.066,35.436c0.925,4.695,4.746,12.063,10.348,17.936   c25.482,26.561,89.012,63.779,199.036,63.779c149.903,0,184.178-49.934,191.177-66.355c5.005-11.744,7.598-32.967,7.598-50.795   c0-4.314-0.108-7.76-0.271-11.143c0-0.549-0.318-0.594-0.428-0.059C402.954,192.295,274.879,245.717,164.204,245.717    M19.741,75.143C13.711,87.11,7.027,107.299,5.04,117.748c-0.871,4.477-0.5,6.627,1.07,9.968   c12.613,26.761,76.412,69.579,225.23,69.579c90.79,0,161.318-22.305,172.744-63.008c2.104-7.493,2.217-15.404-0.486-26.064   c-3.02-11.912-8.676-25.803-13.463-35.557c-0.158-0.318-0.437-0.271-0.38,0.105c1.778,53.386-147.099,87.793-222.216,87.793   c-81.365,0-149.246-32.418-149.246-73.352c0-3.933,0.814-7.867,1.83-11.961C20.225,74.877,19.905,74.815,19.741,75.143    M341.315,9.596c0.864,1.354,1.295,2.799,1.295,4.744c0,22.836-69.891,63.234-181.148,63.234   c-81.748,0-97.053-30.326-97.053-49.612c0-6.894,2.644-13.948,8.467-21.112c0.318-0.426,0.048-0.59-0.319-0.273   c-10.62,9-20.378,19.127-28.938,30.059c-4.09,5.17-6.629,9.75-6.629,12.494c0,39.967,100.216,68.945,193.921,68.945   c99.844,0,144.404-32.594,144.404-61.238c0-10.237-3.985-16.213-14.179-27.799c-6.617-7.537-12.876-13.674-19.501-19.715   C341.315,9.059,341.092,9.274,341.315,9.596 M310.706-13.235c-30.814-18.47-66.597-28.978-105.174-28.978   c-38.846,0-75.707,10.875-106.632,29.834C89.624-6.67,84.403-2.095,84.403,3.786c0,17.336,40.515,35.976,112.394,35.976   c71.133,0,126.305-20.417,126.305-40.07C323.102-4.999,319.002-8.281,310.706-13.235"
              id="path9"
            />
          </g>
        </svg>
      </a>
      <div className="inner-container">
        <h4 className="header-text">
          CMAC Alert Data{" "}
          <span className="alert-originator">{fullData.commonName}</span>
        </h4>
        <p className="date">{date}</p>
      </div>
    </header>
  );
};

export default Header;
