import React, { useEffect, useState } from "react";
import "./atm.css";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const ATMcard = () => {
  const [cardNo, setCardNo] = useState("");
  const [cardHolder, setCardHolder] = useState("");
  const [expDate, setExpDate] = useState("");
  const [cvv, setCvv] = useState();
  const [fname, setFname] = useState("");
  const [lname, setLname] = useState("");
  const [email, setEmail] = useState("");
  const [driverImg, setDriverImg] = useState("");
  const [driverName, setDriverName] = useState("");
  const [driverMobNo, setDriverMobNo] = useState("");
  const [charges, setCharges] = useState("");
  const [stay, setStay] = useState("");
  const [hotel, setHotel] = useState("");
  const [room, setRoom] = useState("");
  const [finalCost, setFinalCost] = useState("");
  const [manageParking, setManageParking] = useState("");
  const [charge, setCharge] = useState("");
  const [carCompany, setCarCompany] = useState("");
  const [carColor, setCarColor] = useState("");
  const [carModel, setCarModel] = useState("");
  const [carType, setCarType] = useState("");
  const [carImg, setCarImg] = useState("");
  const [fuelType, setFuelType] = useState("");
  const [pickUpLocation, setPickUpLocation] = useState("");
  const [pickUpDate, setPickUpDate] = useState("");
  const [dropLocation, setDropLocation] = useState("");
  const [dropDate, setDropDate] = useState("");
  const [pickUpTime, setPickUpTime] = useState("");
  const [members, setMembers] = useState("");
  const [days, setDays] = useState("");
  const [passangerCapacity, setPassangerCapacity] = useState("");
  const navigate = useNavigate();
  const [driverID, setDriverID] = useState("");

  useEffect(() => {
    const auth = localStorage.getItem("driver");
    const data = JSON.parse(auth);
    console.log(data);
    setDriverID(data._id);
    console.log("ID =", data._id);
    setDriverName(data.driverName);
    setDriverMobNo(data.driverMobNo);
    setCharges(data.charges);
    setDriverImg(data.driverImg);
    const auth2 = localStorage.getItem("hotel");
    const data2 = JSON.parse(auth2);
    console.log(data2);
    setStay(data2.stay);
    setHotel(data2.hotel);
    setRoom(data2.room);
    setManageParking(data2.manageParking);
    setFinalCost(data2.finalCost);
    const auth3 = localStorage.getItem("user");
    const data3 = JSON.parse(auth3);
    console.log(data3);
    setFname(data3.fname);
    setLname(data3.lname);
    setEmail(data3.email);
    const auth4 = localStorage.getItem("car");
    const data4 = JSON.parse(auth4);
    setCharge(data4.charge);
    setCarCompany(data4.carCompany);
    setCarColor(data4.carColor);
    setCarModel(data4.carModel);
    setCarType(data4.carType);
    setCarImg(data4.carImg);
    setFuelType(data4.fuelType);
    setPickUpLocation(data4.pickUpLocation);
    setDropLocation(data4.dropLocation);
    setPickUpTime(data4.pickUpTime);
    setPickUpDate(data4.pickUpDate);
    setDropDate(data4.dropDate);
    setPassangerCapacity(data4.passangerCapacity);
    setMembers(data4.members);
    setDays(data4.days);
  }, []);

  const numChangeHandler = (e) => {
    const num = e.target.value;
    if (num.length <= 16) {
      setCardNo(num);
    }
  };

  const nameChangeHandler = (e) => {
    setCardHolder(e.target.value.toUpperCase());
  };

  const dateChangeHandler = (e) => {
    setExpDate(e.target.value);
  };

  const cvvChangeHandler = (e) => {
    const num = e.target.value;
    if (num.length <= 3) {
      setCvv(num);
    }
  };

  // Submit handler and validation.

  const handleSubmit = async (email) => {
    if (cardNo.length === 16 && cardHolder && expDate && cvv) {
      alert("Car Booked Successfully !!!");

      let result = await fetch("http://localhost:9097/atm", {
        method: "POST",
        body: JSON.stringify({
          fname,
          lname,
          email,
          driverImg,
          driverID,
          driverName,
          driverMobNo,
          charges,
          hotel,
          room,
          stay,
          manageParking,
          finalCost,
          charge,
          carCompany,
          carColor,
          carModel,
          carType,
          carImg,
          fuelType,
          pickUpLocation,
          dropLocation,
          pickUpTime,
          pickUpDate,
          dropDate,
          passangerCapacity,
          members,
          days,
          cardNo,
        }),
        headers: {
          "Content-Type": "application/json",
       //   authorization: `bearer ${JSON.parse(localStorage.getItem("token"))}`,
        },
      });
      result = result.json();
      if (result) {
        navigate(`/ticket-details/${email}`);
      }

      setCardNo("");
      setCardHolder("");
      setExpDate("");
      setCvv("");
    } else {
      alert("Please enter vaild card details !!!");
    }
  };

  const convert = (number) => {
    let str = number.toString();
    let temp = "";
    for (let i = 0; i < str.length; i++) {
      if (i === 4 || i === 8 || i === 12) {
        temp = temp + "-" + str[i];
      } else {
        temp = temp + str[i];
      }
    }
    return temp;
  };

  const displayNumber = convert(cardNo);

  return (
    <div className="atm">
      <h4 style={{ padding: "20px" }}> DEBIT CARD DETAILS </h4>
      <div className="atmcard">
        <div className="flip-card">
          <div className="flip-card-inner">
            <div className="flip-card-front">
              <img
                src={
                  "https://uploads.codesandbox.io/uploads/user/dc02524d-7cb1-43f5-89ae-e35176baf665/3mq1-AtmFront.png"
                }
                alt="Avatar"
              />
              <h5 className="number">{displayNumber}</h5>
              <h5 className="date">{expDate}</h5>
              <h5 className="name">{cardHolder}</h5>
            </div>
            <div className="flip-card-back">
              <img
                src={
                  "https://uploads.codesandbox.io/uploads/user/dc02524d-7cb1-43f5-89ae-e35176baf665/J98u-AtmBack.jpeg"
                }
                alt="Avatar"
              />
              <h5 className="cvv">{cvv}</h5>
            </div>
          </div>
        </div>
      </div>

      <div className="details-container">
        {/* <div className="detail-items"> */}
        <h3> Enter Your Card Details </h3>
        <input
          className="numInput"
          name="numInput"
          type="number"
          value={cardNo}
          placeholder="Enter Atm Card Number"
          onChange={numChangeHandler}
        />

        <input
          className="nameInput"
          name="nameInput"
          type="text"
          value={cardHolder}
          placeholder="Enter Name"
          maxLength="20"
          onChange={nameChangeHandler}
        />

        <input
          className="dateInput"
          name="dateInput"
          type="text"
          value={expDate}
          placeholder="Expiry eg. 01/28"
          maxLength="5"
          onChange={dateChangeHandler}
        />

        <input
          className="cvvInput"
          name="cvvInput"
          type="number"
          value={cvv}
          placeholder="Enter CVV"
          maxLength="3"
          onChange={cvvChangeHandler}
        />

        <Button variant="danger" onClick={() => handleSubmit(email)}>
          PAY NOW ( ₹ {finalCost.toLocaleString("en-IN")} )
        </Button>
      </div>
    </div>
  );
};

export default ATMcard;
