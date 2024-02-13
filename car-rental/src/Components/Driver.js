import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";

function Driver() {
  const [data, setData] = useState([]);
  const [driver, setDriver] = useState("");
  const [carAmount, setCarAmount] = useState(0);
  const [rentDays, setRentDays] = useState(0);
  const [totalFair, setTotalFair] = useState(0);
  const [finalCost, setFinalCost] = useState(0);
  const [bookHotel, setBookHotel] = useState(false);
  const [fname, setFname] = useState("");
  const [lname, setLname] = useState("");
  const [email, setEmail] = useState("");
  const [guestCount, setGuestCount] = useState("");
  const [hotel, setHotel] = useState("");
  const [room, setRoom] = useState("");
  const [stay, setStay] = useState(0);
  const [manageParking, setManageParking] = useState("");
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
   
    const data = JSON.parse(localStorage.getItem("car"));
    const user = JSON.parse(localStorage.getItem("user"));
    console.log(data.totalPrice)
    setCarAmount(data.totalPrice);
    setGuestCount(data.members);
    setRentDays(data.days);
    setFname(user.fname);
    setLname(user.lname);
    setEmail(user.email);
    getDrivers();
  }, []);

  useEffect(() => {
    totalCost();
  }, [
    driver,
    totalFair,
    carAmount,
    finalCost,
    bookHotel,
    room,
    stay,
    manageParking,
  ]);

  const getDrivers = async () => {
    let result = await fetch("http://localhost:9094/driver/allDrivers");
    let data = await result.json();
    setData(data);
    console.log(data);
  };

  const check = (driver) => {
    setTotalFair(driver.charges * rentDays);
    setDriver(driver.driverName);
    localStorage.setItem("driver", JSON.stringify(driver));
    // localStorage.setItem("driver", driver.id);
    setError(true);
  };

  const totalCost = () => {
    if (bookHotel) {
      if (room === "" && stay === 0) {
        setFinalCost(carAmount + totalFair);
      } else if (room && stay >= 1) {
        let roomType = Number(room.slice(-4));
        let totalRoomPrice = roomType * stay;
        let cost = carAmount + totalFair + totalRoomPrice;
        setFinalCost(cost);
      }
    } else {
      setFinalCost(carAmount + totalFair);
    }
    if (manageParking === "YES") {
      let roomType = Number(room.slice(-4));
      let totalRoomPrice = roomType * stay;
      let cost = carAmount + totalFair + totalRoomPrice;
      setFinalCost(cost * 0.9);
    }
  };

  console.log(manageParking);
  console.log("HOTEL", hotel);
  console.log("ROOM", room);

  const confirmRide = async () => {
    if (driver) {
      let result = await fetch("http://localhost:9095/hotelBooking", {
        method: "POST",
        body: JSON.stringify({
          hotel,
          room,
          stay,
          manageParking,
          finalCost,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      result = await result.json();
      console.log(result);

      if (result) {
        alert(`Enter Card Details .. Pay & Confirm your ride.. .`);
        localStorage.setItem("hotel", JSON.stringify(result));
        navigate("/ATM");
      }
    } else {
      alert("Select Driver ... ");
    }
  };

  return (
    <div style={{ backgroundColor: "white" }}>
      <h1 style={{ color: "green", textAlign: "center", paddingTop: "50px" }}>
        PROFESSIONAL DRIVERS
      </h1>
      <table>
        <tbody>
          <tr style={{ fontFamily: "cursive", color: "red" }}>
            <td>Sr.No</td>
            <td>IMAGE</td>
            <td>NAME</td>
            <td>EXPERIENCE</td>
            <td>FOOD</td>
            <td>SMOKE</td>
            <td>ALCOHOLIC</td>
            <td>CHARGES ( Per Day )</td>
            <td>CHECK</td>
          </tr>
          {data.map((driver, i) => (
            <tr key={i}>
              <td>{i + 1}</td>
              <td>
                <img
                  style={{ width: "70px" }}
                  src={driver.driverImg}
                  alt="img"
                />
              </td>
              <td>{driver.driverName}</td>
              <td>{driver.drivingExperiance}</td>
              <td>{driver.food}</td>
              <td>{driver.smoke}</td>
              <td>{driver.drink}</td>
              <td>₹ {driver.charges.toLocaleString("en-IN")}</td>
              <td>
                <Button onClick={() => check(driver)}>Check Total</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <h2
        style={{
          textAlign: "center",
          color: "purple",
          paddingBottom: "10px",
          fontFamily: "monospace",
        }}
      >
        DRIVER - {driver}
      </h2>
      <div
        style={{
          textAlign: "center",
          display: "flex",
          justifyContent: "space-evenly",
          alignItems: "center",
          marginBottom: "50px",
        }}
      >
        <h3>TRIP = ( {rentDays} Day )</h3>
        <h3>CAR RENT = ₹ {carAmount.toLocaleString("en-IN")} </h3>
        <h3>DRIVER CHARGES = ₹ {totalFair.toLocaleString("en-IN")}</h3>
        <h3>TOTAL = ₹ {finalCost.toLocaleString("en-IN")} </h3>
      </div>
      <div
        style={{
          display: "flex",
          width: "100%",
          justifyContent: "center",
          marginBottom: "40px",
        }}
      >
        <Button
          style={{ marginRight: "30px" }}
          variant="danger"
          onClick={() => setBookHotel(true)}
        >
          BOOK HOTEL ⬇️
        </Button>{" "}
        {error ? (
          <Button
            style={{ marginLeft: "30px" }}
            variant="success"
            disabled={bookHotel}
            onClick={confirmRide}
          >
            PROCEED ( PAY ) ➡️
          </Button>
        ) : (
          <Button style={{ marginLeft: "30px" }} variant="success" disabled>
            PROCEED ( PAY ) ➡️
          </Button>
        )}
      </div>
      {bookHotel ? (
        <div className="inputContainer">
          <h1
            style={{
              fontFamily: "cursive",
              color: "orangered",
              margin: "20px auto",
            }}
          >
            HOTEL BOOKING
          </h1>

          <form>
            <label>
              <h6> FIRSTNAME : </h6>
            </label>{" "}
            <input type="text" value={fname} readOnly />
            <br />{" "}
            <label>
              <h6> LASTNAME : </h6>
            </label>{" "}
            <input type="text" value={lname} readOnly /> <br />{" "}
            <label>
              <h6> EMAIL : </h6>
            </label>{" "}
            <input type="text" value={email} readOnly />
            <br />{" "}
            <label>
              <h6> GUESTS COUNT : </h6>
            </label>{" "}
            <input type="text" value={guestCount} readOnly />
            <br />
            <label>
              <h6> HOTEL : </h6>{" "}
            </label>
            <select onChange={(e) => setHotel(e.target.value)}>
              <option value="">Select Hotel</option>
              <option value="IbIs International">IbIs International</option>
              <option value="J W Marriott">J W Marriott </option>
              <option value="The Ritz-Carlton">The Ritz-Carlton</option>
              <option value="Lemon Tree Premier">Lemon Tree Premier</option>
              <option value="The Central Park">The Central Park</option>
              <option value="The Atmos Resort">The Atmos Resort</option>
              <option value="CONRAD Club">CONRAD Club</option>
            </select>
            <br />
            <br />
            <br />
            <label>
              <h6> ROOM PRICE ( Per Night ) : </h6>{" "}
            </label>
            <select onChange={(e) => setRoom(e.target.value)}>
              <option value="">Select Room</option>
              <option value="Classic = ₹ 1800">Classic = ₹ 1800</option>
              <option value="Delux = ₹ 2400">Delux = ₹ 2400 </option>
              <option value="Royal Suite = ₹ 3200">Royal Suite = ₹ 3200</option>
            </select>
            <br />
            <br />
            <br />
            <label>
              <h6> STAY ( Nights ) : </h6>{" "}
            </label>
            <input type="number" onChange={(e) => setStay(e.target.value)} />
            <br />
            <h5 style={{ paddingTop: "20px", color: "red" }}>
              * Manage Parking Charges ( Get 10% OFF - On Total )
            </h5>
            <br />
            <label style={{ float: "left", paddingLeft: "8px" }}>
              {" "}
              PARKING CHARGES :{" "}
            </label>
            <span>
              <input
                type="radio"
                value="YES"
                onChange={() => setManageParking("YES")}
                checked={manageParking === "YES"}
              />
            </span>
            <span>YES</span>
            <span>
              <input
                type="radio"
                name="gender"
                value="NO"
                onChange={() => setManageParking("NO")}
                checked={manageParking === "NO"}
              />
            </span>
            <span>NO</span>
            <h3 style={{ float: "right", color: "green" }}>
              TOTAL = ₹ {finalCost.toLocaleString("en-IN")}
            </h3>
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                width: "100%",
              }}
            >
              {stay <= rentDays && stay > 0 ? (
                <Button onClick={() => confirmRide()}>PAY NOW</Button>
              ) : (
                <Button disabled>PAY NOW</Button>
              )}
            </div>
          </form>
        </div>
      ) : null}
    </div>
  );
}

export default Driver;
