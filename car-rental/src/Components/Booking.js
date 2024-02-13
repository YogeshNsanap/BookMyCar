import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";

function Booking() {
  const params = useParams();
  const [carImg, setCarImg] = useState("");
  const [carCompany, setCarCompany] = useState("");
  const [carColor, setCarColor] = useState("");
  const [carModel, setCarModel] = useState("");
  const [carType, setCarType] = useState("");
  const [carOwner, setCarOwner] = useState("");
  const [numPlate, setNumPlate] = useState("");
  const [insuranceValidity, setInsuranceValidity] = useState("");
  const [passangerCapacity, setPassangerCapacity] = useState("");
  const [fuelType, setFuelType] = useState("");
  const [feature, setFeature] = useState("");
  const [charge, setCharge] = useState(0);
  const [pickUpLocation, setPickUpLocation] = useState("");
  const [dropLocation, setDropLocation] = useState("");
  const [pickUpDate, setPickUpDate] = useState("");
  const [dropDate, setDropDate] = useState("");
  const [pickUpTime, setPickUpTime] = useState("");
  const [members, setMembers] = useState(1);
  const [numBags, setNumBags] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);
  const [days, setDays] = useState(0);
  const [currentDate, setCurrenrDate] = useState("");
  const [currTime, setCurrTime] = useState("");
  const [error, setError] = useState(false);

  const navigate = useNavigate();

  useEffect(() => {
    getCarDetails();
  }, []);

  useEffect(() => {
    let curr = new Date().toISOString();
    curr = curr.slice(0, 10);
    setCurrenrDate(curr);

    const calcDate = pickUpDate;
    const pickUp = new Date(calcDate);

    const calcDate2 = dropDate;
    const drop = new Date(calcDate2);

    const day = drop.getDate() - pickUp.getDate();
    const pickMonth = pickUp.getMonth() + 1;
    const dropMonth = drop.getMonth() + 1;

    const calculateDays = Math.abs(day);

    if (dropDate >= pickUpDate && pickMonth === dropMonth) {
      setDays(calculateDays === 0 ? 1 : calculateDays);

      const min = [new Date().getMinutes()];
      const hr = [new Date().getHours()];

      if (hr.indexOf(0) === [0 - 9] && min.indexOf(0) === [0]) {
        let realT1 = `0${hr}:0${min}`;
        setCurrTime(realT1);
      } else {
        let realT2 = `${hr}:${min}`;
        setCurrTime(realT2);
      }

      console.log(currTime);
      console.log(pickUpTime);

      if (pickUpTime < currTime && pickUpDate === currentDate) {
        setError(true);
      } else if (pickUpDate < currentDate) {
        setError(true);
      } else {
        setError(false);
      }
      if (members <= 0) {
        setError(true);
      }
    }
    if (pickUpDate < currentDate) {
      setDays(0);
    }
    if (dropDate < currentDate) {
      setDays(0);
    }
    if (dropDate < pickUpDate) {
      setError(true);
    }
  }, [pickUpDate, dropDate, pickUpTime, members, numBags]);

  useEffect(() => {
    setTotalPrice(charge * Number(days));
  });
  let carId = params.id;

  const getCarDetails = async () => {
    // console.log(params);
    let result = await fetch(`http://localhost:9092/car/${params.id}`);
    result = await result.json();
    console.warn(result);
    setCarImg(result.carImg);
    setCarCompany(result.carCompany);
    setCarModel(result.carModel);
    setCarType(result.carType);
    setCarOwner(result.carOwner);
    setCarColor(result.carColor);
    setNumPlate(result.numPlate);
    setInsuranceValidity(result.insuranceValidity);
    setFeature(result.feature);
    setFuelType(result.fuelType);
    setPassangerCapacity(result.passangerCapacity - 1);
    setCharge(result.charge);
  };
  console.log(carId+"carId is");
  const collectData = async () => {
    if (
      !pickUpLocation ||
      !pickUpDate ||
      !pickUpTime ||
      !dropLocation ||
      !dropDate ||
      !members ||
      !numBags ||
      members > passangerCapacity ||
      members === 0 ||
      days === 0 ||
      numBags > 4
    ) {
      alert("Enter valid credentials");
      return;
    } else {
   
      let result = await fetch(`http://localhost:9093/booking`, {
        method: "POST",
        body: JSON.stringify({
          carId,
          carCompany,
          carColor,
          carModel,
          carType,
          carImg,
          carOwner,
          numPlate,
          insuranceValidity,
          fuelType,
          feature,
          pickUpLocation,
          dropLocation,
          pickUpTime,
          pickUpDate,
          dropDate,
          passangerCapacity,
          members,
          days,
          numBags,
          totalPrice,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      result = await result.json();
      console.log(result);

      if (result) {
        alert(`Car Confirmed 🚗 Select your driver.. . 🙎‍♂️`);
        localStorage.setItem("car", JSON.stringify(result));
        navigate("/driver");
      }
    }
  };

  return (
    <div className="inputContainer">
      <h1
        style={{
          fontFamily: "cursive",
          color: "black",
          margin: "20px auto",
        }}
      >
        REGISTER BOOKING
      </h1>
      <h4 style={{ color: "green" }}>
        {carModel} - Per Day ( ₹ {charge} )
      </h4>
      <h5>Passanger Capacity = {passangerCapacity}</h5>
      <form>
        <label>
          <h6> PICKUP LOCATION : </h6>
        </label>{" "}
        <input
          type="text"
          placeholder="Enter Pickup location"
          onChange={(e) => {
            setPickUpLocation(e.target.value.toUpperCase());
          }}
          required
        />
        <br />
        <label>
          <h6> DROP LOCATION : </h6>
        </label>{" "}
        <input
          type="text"
          placeholder="Enter Drop location"
          onChange={(e) => {
            setDropLocation(e.target.value.toUpperCase());
          }}
          required
        />
        <br />
        <label>
          <h6> PICKUP DATE : </h6>
        </label>{" "}
        <input
          type="date"
          onChange={(e) => {
            setPickUpDate(e.target.value);
          }}
          required
        />
        <br />
        <label>
          <h6> DROP DATE : </h6>
        </label>{" "}
        <input
          type="date"
          onChange={(e) => {
            setDropDate(e.target.value);
          }}
          required
        />
        <br />{" "}
        <label>
          {" "}
          <h6>TRIP DURATION ( In Days ) : </h6>
        </label>
        <input
          type="number"
          id="days"
          value={days}
          name="days"
          disabled
          readOnly
        />
        <br />
        <label>
          <h6> PRICE ( ₹ {charge} / Day ) : </h6>
        </label>{" "}
        <input type="number" value={totalPrice} required disabled readOnly />
        <br />
        <label>
          <h6> PICKUP TIME : </h6>
        </label>{" "}
        <input
          type="time"
          onChange={(e) => {
            setPickUpTime(e.target.value);
          }}
          required
        />
        <br />
        <label>
          <h6> TOTAL MEMBERS : </h6>
        </label>{" "}
        <input
          type="number"
          placeholder="Enter Total Members"
          onChange={(e) => {
            setMembers(e.target.value);
          }}
          required
        />
        <br />
        <label>
          <h6>BAGS / LUGGAGES : </h6>
        </label>{" "}
        <input
          type="number"
          placeholder="Enter Number of Bags ( Max. Limit - 4 )"
          onChange={(e) => {
            setNumBags(e.target.value);
          }}
          required
        />
        <br />
        {!error ? (
          <Button variant="danger" onClick={collectData}>
            Submit
          </Button>
        ) : (
          <Button variant="danger" onClick={collectData} disabled>
            Submit
          </Button>
        )}
      </form>
    </div>
  );
}

export default Booking;
