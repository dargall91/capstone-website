import { useState } from "react";
import { useLoginContext } from "../LoginContext";

const Login = () => {
  const [user, setUser] = useState("");

  const { setAlertOriginator, setLogin } = useLoginContext();

  const handleChange = (e) => {
    setUser(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (user) {
      setAlertOriginator(user.replace("@", "%40"));
      setLogin(true);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
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
      <div className="inner-form">
        <input
          className="form-input"
          type="text"
          placeholder="Alert Originator"
          onChange={handleChange}
          value={user}
        ></input>

        <button className="btn" type="submit">
          Login
        </button>
      </div>
      <div className="bg"></div>
      <a href="https://www.asu.edu/" target="_blank" rel="noreferrer">
        <svg
          className="asu-logo"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 166.35873 31.62462"
        >
          <g transform="matrix(1.25 0 0 -1.25 -299.74 505.57)">
            <path
              d="m132.34 1.1641c-1.96 0-4.57 0.8701-4.57 3.8007 0 2.448 2.36 2.9469 3.06 3.0918 0.61 0.1289 1.24 0.2086 1.86 0.2891 0.84 0.1128 1.99 0.2582 1.99 1.1602 0 0.7891-0.97 1.3201-2.1 1.3201-0.66 0-1.43-0.144-2.19-0.547-0.56-0.3057-0.8-0.5643-1.09-0.8864l-2.16 1.3694c0.71 0.821 1.98 2.302 5.18 2.302 1.6 0 3.24-0.338 4.27-1.529 0.6-0.692 0.9-1.5945 0.9-2.4159 0-1.2884-0.66-2.302-1.96-2.7851-0.64-0.2255-2.27-0.5328-3.48-0.6778-0.48-0.0644-1.45-0.1928-1.45-1.0624 0-0.5476 0.56-1.1915 1.69-1.1915 0.73 0 1.45 0.2746 1.85 0.5 0.45 0.2577 0.78 0.5642 1.13 0.918l2.1-1.498c-0.99-0.8858-2.41-2.1582-5.03-2.1582zm-45.883 0.2578v1.916h2.447v-1.916h-2.447zm-81.461 0.0136c-0.2209 0-0.3022 0.0716-0.1895 0.2266 0 0 0.4118 0.3909 1.1602 1.2481 1.0373 1.1852 0.6328 2.7675 0.6328 2.7675l-5.5547 22.222h8.0821l1.162-4.879h7.801l1.215 4.879h10.64c4.369 0 7.921-0.444 9.672-3.17 1.31 2.488 4.13 3.654 9.106 3.225 1.733-0.148 3.429-0.958 4.156-2.25l0.344 1.072c0.286 0.65 0.88 1.125 1.726 1.125h8.129c0.221 0 0.292-0.066 0.18-0.22 0 0-0.436-0.483-1.156-1.262-0.892-0.959-0.844-1.975-0.844-1.975v-23.01l-8.707 0.0005v18.402c-0.107 1.743-1.81 2.185-2.75 2.154-1.351-0.041-2.137-0.803-2.137-2.101v-18.456l-8.48 0.0005s-3.542 0.004-5.977 0.004c-4.642 0-9.022 0.7148-10.504 4.5781l-0.875-3.4219c-0.214-0.6799-0.946-1.1602-1.674-1.1602h-15.158zm67.977 0.002l-4.219 11.37 3.027-0.001 0.565-1.819h4.187l0.563 1.819h3.027l-4.187-11.37-2.963 0.0005zm65.747 0.5313v2.6093h-1.23v1.5137h1.23v4.0582c0 0.806 0.01 1.402 0.35 1.901 0.53 0.773 1.47 0.82 2.32 0.82 0.45 0 0.77-0.048 1.31-0.129v-1.931l-0.89 0.033c-0.66 0-0.66-0.436-0.64-0.9514v-3.7852h1.64v-1.5136h-1.63v-2.625h-2.46zm13.74 0v2.6093h-1.23v1.5137h1.23v4.0582c0 0.806 0.01 1.402 0.35 1.901 0.53 0.773 1.47 0.82 2.32 0.82 0.45 0 0.77-0.048 1.3-0.129v-1.931l-0.88 0.033c-0.66 0-0.66-0.436-0.65-0.9514v-3.7852h1.64v-1.5136h-1.62v-2.625h-2.46zm8.72 2.1582c-3.31 0-4.31 2.6094-4.31 4.6386 0 2.7544 1.75 4.3474 4.25 4.3474 1.35 0 2.45-0.515 3.17-1.191 0.44-0.403 0.73-0.821 1.02-1.256l-1.87-0.9512c-0.31 0.5962-0.78 1.4822-2.13 1.4822-1.33 0-1.93-1.047-1.91-2.0935h6c-0.03-0.9502-0.09-2.5924-1.19-3.7676-1.08-1.1596-2.53-1.2089-3.03-1.2089zm-58.85 0.0097c-2.832 0.0878-4.262 2.0354-4.262 4.4512 0 3.1081 2.162 4.4611 4.522 4.4611 3.29 0 4.51-2.335 4.51-4.4611 0-1.53-0.75-4.252-4.18-4.4453-0.21-0.0111-0.4-0.0117-0.59-0.0059zm-27.924 0.0059l1.483 4.7988h-2.883c0.258-0.8536 0.692-2.1894 0.933-3.043 0.161-0.5797 0.322-1.1765 0.467-1.7558zm45.874 0.0332c-0.99 0-1.98 0.1282-2.76 0.7402-0.75 0.5954-0.83 1.2893-0.88 1.8692h2.48c0.06-0.306 0.21-0.9825 1.3-0.9825 0.89 0 0.92 0.6428 0.95 1.0293v0.4512c-1.04 0.1933-2.64 0.4835-3.32 0.7734-0.69 0.2899-1.77 1.0632-1.77 2.4164 0 1.175 0.82 2.56 2.76 2.56 1.24 0 1.95-0.533 2.57-1v0.774h2.43c-0.19-0.29-0.24-0.419-0.24-1.321v-4.8942c0-2.1742-2.49-2.416-3.52-2.416zm26.93 0c-0.99 0-1.99 0.1282-2.76 0.7402-0.76 0.5954-0.84 1.2893-0.88 1.8692h2.47c0.07-0.306 0.21-0.9825 1.31-0.9825 0.88 0 0.92 0.6428 0.95 1.0293v0.4512c-1.05 0.1933-2.64 0.4835-3.32 0.7734-0.69 0.2899-1.77 1.0632-1.77 2.4164 0 1.175 0.82 2.56 2.75 2.56 1.24 0 1.95-0.533 2.58-1v0.774h2.43c-0.19-0.29-0.24-0.419-0.24-1.321v-4.8942c0-2.1742-2.49-2.416-3.52-2.416zm-34.41 0.0156c-0.59 0-1.14 0.1456-1.53 0.3066-0.43 0.2094-0.59 0.402-0.83 0.6602v-0.7422h-2.5v8.391h2.48v-4.5414c0-1.0468 0-2.0937 1.3-2.0937 0.49 0 0.92 0.1941 1.12 0.6445 0.09 0.2255 0.11 0.4989 0.11 1.2559v4.7347h2.46v-5.7484c0-1.3045-0.22-2.0627-1.11-2.5293-0.43-0.2416-1-0.3379-1.5-0.3379zm-27.196 0.1133c-1.289 0.1288-1.885 0.4983-2.432 0.9648v-0.8847h-2.416v8.4222h2.48v-4.7347c0-1.063 0.354-1.2399 1.545-1.4493l0.823-0.1289v-2.1894zm0.853 0.0801v8.4222h2.447v-8.4222h-2.447zm3.961 0.0156v1.9004h4.059l-4.541 4.6382v1.868h7.681v-1.932h-4.476l4.476-4.5742v-1.9004h-7.199zm70.752 1.498c0.43 0 0.9 0.1603 1.21 0.4668 0.35 0.37 0.42 0.8371 0.46 1.1914h-3.43c0.08-0.3543 0.16-0.7409 0.5-1.1113 0.24-0.2572 0.66-0.5469 1.26-0.5469zm-58.42 0.2012c0.34 0.0136 0.76 0.1123 1.15 0.4746 0.47 0.451 0.69 1.1772 0.69 2.1113 0 1.3205-0.66 2.3985-2 2.3985-1.38 0-2.03-1.175-2.03-2.5117 0-0.5798 0.12-1.1595 0.42-1.6426 0.34-0.531 0.76-0.7734 1.47-0.8223 0.09-0.008 0.19-0.0123 0.3-0.0078zm-88.557 1.2344l2.7 10.867h-5.391l2.691-10.867zm107.18 1.4472c-0.01 0.8697-0.05 1.6438-0.87 2.1578-0.26 0.162-0.55 0.274-0.85 0.274-0.58 0-0.94-0.451-0.94-0.854 0-0.6438 0.65-0.8855 1.07-1.0465l1.59-0.5313zm26.93 0c-0.02 0.8697-0.05 1.6438-0.87 2.1578-0.26 0.162-0.55 0.274-0.85 0.274-0.58 0-0.94-0.451-0.94-0.854 0-0.6438 0.65-0.8855 1.07-1.0465l1.59-0.5313zm-59.415 7.7388v1.918h2.449v-1.918h-2.449zm34.325 0v1.918h2.44v-1.918h-2.44zm-54.085 0.017v6.344c-0.016 1.546-0.016 3.544 2.174 4.672 0.772 0.402 1.787 0.611 2.64 0.611 0.484 0 3.013-0.098 4.237-2.062 0.627-1.031 0.642-2.126 0.658-3.221v-6.344h-2.85v7.068c-0.016 0.886-0.016 2.207-1.949 2.207-0.708 0-1.481-0.177-1.836-0.773-0.193-0.354-0.209-0.836-0.209-1.416v-7.086h-2.865zm58.625 0.531v2.61h-1.23v1.511h1.23v4.059c0 0.806 0.01 1.401 0.35 1.9 0.53 0.773 1.47 0.823 2.32 0.823 0.45 0 0.77-0.049 1.3-0.129v-1.932l-0.88 0.031c-0.66 0-0.66-0.433-0.64-0.949v-3.785h1.64v-1.514h-1.63v-2.625h-2.46zm-23.37 2.159c-3.32 0-4.32 2.608-4.32 4.638 0 2.754 1.76 4.346 4.25 4.346 1.36 0 2.45-0.513 3.18-1.19 0.43-0.402 0.72-0.822 1.01-1.257l-1.87-0.95c-0.3 0.596-0.77 1.481-2.12 1.481-1.34 0-1.94-1.048-1.92-2.094h6.01c-0.03-0.95-0.1-2.592-1.19-3.767-1.08-1.16-2.53-1.207-3.03-1.207zm14.14 0c-2.35 0-3.54 1.433-3.54 2.818 0 0.628 0.25 1.208 0.62 1.578s0.89 0.579 1.4 0.725c0.37 0.112 0.76 0.193 1.13 0.257 0.48 0.081 1.06 0.129 1.43 0.323 0.15 0.08 0.33 0.192 0.33 0.482 0 0.628-0.91 0.82-1.5 0.82-0.94 0-1.77-0.497-2.45-1.158l-1.56 1.416c0.53 0.5 1.74 1.627 4.07 1.627 1.98 0 3.98-0.966 3.98-2.947 0-1.997-1.96-2.448-3.65-2.754-0.81-0.145-1.34-0.241-1.34-0.725 0-0.241 0.22-0.627 1.19-0.627 0.58 0 1.32 0.225 2.11 0.836l1.53-1.416c-0.58-0.402-1.77-1.255-3.75-1.255zm-33.532 0.064c-0.596 0-1.144 0.144-1.531 0.305-0.435 0.209-0.594 0.402-0.836 0.66v-0.74h-2.498v8.39h2.481v-4.541c0-1.047 0-2.094 1.304-2.094 0.483 0 0.918 0.194 1.112 0.645 0.096 0.226 0.113 0.499 0.113 1.256v4.734h2.463v-5.75c0-1.304-0.226-2.061-1.112-2.527-0.434-0.242-0.996-0.338-1.496-0.338zm29.452 0.111c-1.29 0.129-1.88 0.501-2.43 0.967v-0.885h-2.41v8.422h2.48v-4.734c0-1.064 0.35-1.24 1.54-1.449l0.82-0.129v-2.192zm-25.555 0.082v8.422h2.449v-8.422h-2.449zm34.325 0v8.422h2.44v-8.422h-2.44zm-31.151 0.016l2.72 8.406h2.594l2.717-8.406h-2.541l-0.516 2.061c-0.499 1.981-0.546 2.19-0.885 4.058-0.273-1.61-0.451-2.383-1.482-6.119h-2.607zm40.361 0l2.51 8.117c0.05 0.177 0.11 0.385 0.11 0.594 0 0.596-0.63 0.598-1.03 0.613h-0.87v1.965h1.79c0.64-0.016 1.21-0.048 1.79-0.676 0.42-0.467 0.59-0.983 0.79-1.531l2.96-9.082h-2.64l-1.39 6.023-1.38-6.023h-2.64zm-28.06 1.498c0.44 0 0.9 0.161 1.21 0.467 0.35 0.37 0.42 0.837 0.47 1.191h-3.43c0.08-0.354 0.16-0.74 0.5-1.111 0.24-0.257 0.66-0.547 1.25-0.547z"
              transform="matrix(.8 0 0 -.8 239.79 404.46)"
              fill="#A30046"
            />
            <path
              d="m39.391 3.2188c-0.041 0.0046-0.081 0.0262-0.114 0.0664l-3.32 4.1074s-1.667-0.7063-3.238-0.7188c-1.464-0.012-2.547 0.2492-3.143 0.8321-0.345 0.3386-0.517 0.7734-0.517 1.2968 0 0.7142 0.404 1.3043 1.267 1.8513 0.774 0.483 1.845 0.894 2.983 1.323 1.481 0.564 3.167 1.2 4.548 2.152 2.643 1.822 2.85 4.109 2.85 4.109l5.104 1.151c0.188 0.042 0.274-0.226 0.097-0.303l-4.988-2.178c-0.044-0.019-0.027-0.086 0.021-0.084l5.539 0.356c0.194 0.012 0.237-0.267 0.049-0.315l-5.324-1.363c-0.048-0.012-0.043-0.081 0.006-0.086l5.525-0.516c0.192-0.017 0.192-0.296 0-0.314l-5.525-0.516c-0.049-0.004-0.053-0.071-0.006-0.084l5.324-1.365c0.187-0.048 0.145-0.327-0.049-0.314l-5.539 0.355c-0.048 0.003-0.065-0.062-0.021-0.082l4.988-2.18c0.177-0.077 0.091-0.345-0.097-0.302l-5.411 1.22c-0.047 0.011-0.074-0.052-0.033-0.078l4.477-2.9158c0.165-0.1077 0.034-0.3606-0.149-0.2871l-5.086 2.0429c-0.044 0.018-0.082-0.041-0.047-0.074l3.84-3.5626c0.147-0.1369-0.027-0.3698-0.199-0.2657l-4.613 2.8067c-0.041 0.0247-0.085-0.0262-0.057-0.0645l3.1-4.1191c0.122-0.1621-0.093-0.3619-0.246-0.2285l-4.012 3.4922c-0.036 0.0311-0.088-0.01-0.066-0.0528l2.257-4.5429c0.069-0.1386-0.054-0.264-0.175-0.25zm-18.487 2.9335c-0.114 0.0276-0.186 0.1833-0.076 0.2852l1.518 1.4082c0.034-0.226 0.092-0.5466 0.14-0.7852l-1.459-0.8867c-0.042-0.026-0.085-0.0306-0.123-0.0215zm-1.492 1.8809c-0.106 0.0453-0.148 0.2067-0.025 0.2871l2.935 1.9117c-0.035-0.3323-0.05-0.7726-0.049-1.0992l-2.738-1.0996c-0.045-0.0182-0.088-0.0151-0.123 0zm-0.986 2.0648c-0.189-0.043-0.275 0.225-0.098 0.302l4.623 2.02c-0.204-0.399-0.39-0.928-0.496-1.412l-4.029-0.91zm-0.676 2.209c-0.192-0.012-0.238 0.264-0.051 0.312l5.344 1.369c0.045 0.012 0.04 0.077-0.006 0.082l-5.545 0.516c-0.19 0.018-0.19 0.296 0 0.314l5.533 0.516c0.048 0.005 0.053 0.073 0.006 0.086l-5.332 1.367c-0.186 0.048-0.141 0.323 0.051 0.311l5.547-0.356c0.048-0.003 0.064 0.065 0.019 0.084l-4.988 2.178c-0.177 0.077-0.091 0.345 0.098 0.303l5.39-1.215c0.052-0.012 0.081 0.057 0.038 0.086l-4.467 2.906c-0.164 0.107-0.034 0.36 0.148 0.287l5.074-2.037c0.049-0.02 0.089 0.042 0.051 0.078l-3.836 3.561c-0.146 0.135 0.027 0.365 0.197 0.261l4.594-2.793c0.048-0.029 0.1 0.03 0.067 0.075l-3.075 4.084c-0.122 0.163 0.095 0.362 0.248 0.228l3.965-3.451c0.045-0.039 0.111 0.011 0.084 0.064l-2.234 4.495c-0.092 0.185 0.159 0.346 0.289 0.185l3.262-4.043s0.926 0.456 1.808 0.539c1.566 0.149 2.989-0.195 3.768-0.904 0.405-0.368 0.607-0.815 0.607-1.32 0-1.894-2.112-2.624-4.57-3.469-2.524-0.876-5.398-1.868-6.76-4.358l-5.324-0.341zm23.002 6.189c0.102 0.506 0.092 1.406 0.092 1.406l3.851 1.549c0.184 0.074 0.314-0.181 0.149-0.289l-4.092-2.666zm0.062 2.147c-0.018 0.404-0.119 1.142-0.119 1.142l2.52 1.531c0.17 0.104 0.345-0.126 0.199-0.261l-2.6-2.412zm-0.406 2.416c-0.095 0.314-0.277 0.761-0.277 0.761l1.256 1.096c0.153 0.133 0.368-0.068 0.246-0.23l-1.225-1.627z"
              transform="matrix(.8 0 0 -.8 239.79 404.46)"
              fill="#ffc425"
            />
          </g>
        </svg>
      </a>
    </form>
  );
};

export default Login;
