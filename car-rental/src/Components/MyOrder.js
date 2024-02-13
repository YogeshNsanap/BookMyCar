import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function MyOrder() {
  const [ticket, setTicket] = useState([]);
  const params = useParams();
  console.log(params);

  useEffect(() => {
    getTicket();
  }, []);

  const getTicket = async () => {
    const result = await fetch(`http://localhost:9097/atm/${params.email}`);
    const data = await result.json();
    console.log("FINAL TICKET", data);
    setTicket(data);

    console.log(ticket);
  };

  return (
    <div>
      <h1 style={{ textAlign: "center", color: "red" }}>MY BOOKING</h1>
      <table>
        <tbody>
          <tr>
            <td>Sr.No</td>
            <td>CAR</td>
            <td>MODEL</td>
            <td>IMG</td>
            <td>RENT/DAY</td>
            <td>PICKUP LOCATION</td>
            <td>PICKUP DATE</td>
            <td>PICKUP TIME</td>
            <td>DROP LOCATION</td>
            <td>DROP DATE</td>
            <td>DRIVER IMG</td>
            <td>DRIVER NAME</td>
            <td>DRIVER MobNo.</td>
            <td>HOTEL</td>
            <td>ROOM</td>
            <td>STAY</td>
            <td>TOTAL COST</td>
            <td>BOOKING ID</td>
          </tr>
          {ticket.length > 0 ? (
            ticket.map((book, i) => (
              <tr>
                <td>{i + 1}</td>
                <td>{book.carCompany}</td>
                <td>{book.carModel}</td>
                <td>
                  <img style={{ width: "150px" }} src={book.carImg} alt="" />
                </td>
                <td>{book.charges.toLocaleString("en-IN")}</td>
                <td>{book.pickUpLocation}</td>
                <td>{book.pickUpDate}</td>
                <td>{book.pickUpTime}</td>
                <td>{book.dropLocation}</td>
                <td>{book.dropDate}</td>
                <td>
                  <img
                    style={{ width: "60px" }}
                    src={book.driverImg}
                    alt="img"
                  />
                </td>
                <td>{book.driverName}</td>
                <td>{book.driverMobNo}</td>
                <td>{book.hotel}</td>
                <td>{book.room}</td>
                <td>{book.stay}</td>
                <td>₹ {book.finalCost.toLocaleString("en-IN")}</td>
                <td>{book._id}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="17" style={{ color: "red" }}>
                <h3> NO RECORD</h3>
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
export default MyOrder;