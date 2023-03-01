import { useNavigationContext } from "../NavigationContext";
import { useState, createRef } from "react";

const Filters = () => {
  const [mType, setMType] = useState("");
  const [mNum, setMNum] = useState("");
  const [frDate, setFrDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [sortBy, setSortBy] = useState("date");
  const [sortOrder, setSortOrder] = useState("desc");

  const { buildFilters, setPage } = useNavigationContext();

  const from = createRef();
  const to = createRef();

  const handleMessageType = (e) => {
    setMType(e.target.value);
  };

  const handleMessageNumber = (e) => {
    setMNum(e.target.value);
  };

  const handleFromDate = (e) => {
    setFrDate(e.target.value);
  };

  const handleToDate = (e) => {
    setToDate(e.target.value);
  };

  const clearDates = () => {
    from.current.value = "";
    to.current.value = "";
    setFrDate("");
    setToDate("");
  };

  const handleSortBy = (e) => {
    setSortBy(e.target.value);
  };

  const handleSortOrder = (e) => {
    setSortOrder(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    let item = {
      mType: mType,
      mNum: mNum,
      frDate: frDate,
      toDate: toDate,
      sortBy: sortBy,
      sortOrder: sortOrder,
    };
    setPage(1);
    buildFilters(item);
  };

  return (
    <form className="filter-form" onSubmit={handleSubmit}>
      <div className="message-type-container">
        <select className="message-type-box" onChange={handleMessageType}>
          <option value="">Message Type</option>
          <option value="Alert">Alert</option>
          <option value="Update">Update</option>
          <option value="Test">Test</option>
        </select>

        <input
          className="filter-input-numbers"
          type="text"
          placeholder="Message Number"
          onChange={handleMessageNumber}
          value={mNum}
          size="14"
        ></input>
      </div>

      <div className="date-container">
        <div className="from-container">
          <label htmlFor="fromDate">From: </label>
          <input
            type="date"
            ref={from}
            className="filter-date"
            id="fromDate"
            onChange={handleFromDate}
          ></input>
        </div>

        <div className="to-container">
          <label htmlFor="toDate">To: </label>
          <input
            type="date"
            ref={to}
            className="filter-date"
            id="toDate"
            onChange={handleToDate}
          ></input>
        </div>

        <div className="clear-btn-container">
          <button
            type="button"
            className="btn btn-hipster"
            onClick={clearDates}
          >
            Clear
          </button>
        </div>
      </div>

      <div className="sort-by-container" onChange={handleSortBy}>
        <p className="sort-by-text">Sort By:</p>
        <div className="inner-radio">
          <input
            type="radio"
            id="date"
            value="date"
            name="sortBy"
            defaultChecked={true}
          ></input>
          <label htmlFor="date">Date</label>
        </div>

        <div className="inner-radio">
          <input type="radio" id="number" value="number" name="sortBy"></input>
          <label htmlFor="number">Number</label>
        </div>
      </div>

      <div className="sort-order-container" onChange={handleSortOrder}>
        <p className="sort-order-text">Order By:</p>

        <div className="inner-radio">
          <input
            type="radio"
            id="desc"
            value="desc"
            name="sortOrder"
            defaultChecked={true}
          ></input>
          <label htmlFor="desc">Descending</label>
        </div>

        <div className="inner-radio">
          <input type="radio" id="asc" value="asc" name="sortOrder"></input>
          <label htmlFor="asc">Ascending</label>
        </div>
      </div>

      <div className="filter-btn-container">
        <button className="filter-btn btn" type="submit">
          Filter
        </button>
      </div>
    </form>
  );
};

export default Filters;
