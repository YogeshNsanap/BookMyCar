import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";

function CarListing() {
  const [car, setCar] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getCar();
  }, []);

  const getCar = async () => {
    let result = await fetch("http://localhost:9092/car/allCars", {
      headers: {
        authorization: `bearer ${JSON.parse(localStorage.getItem("auth"))}`,
      },
    });
    let data = await result.json();
    console.log("Car List -", data);
    setCar(data);
  };

  const bookMyRide = async (id) => {
    navigate(`/book-car/${id}`);
  };

  return (
    <div className="product-table">
      <h1 style={{ color: "green", fontFamily: "cursive" }}>
        BOOK YOUR RIDE ... 🚗
      </h1>
      <div className="cards">
        {car.map((item, i) => (
          <Card
            style={{
              width: "32rem",
              border: "2px solid black",
              margin: "30px",
            }}
            key={i}
          >
            <Card.Img variant="top" src={item.carImg} alt="ProductImg" />
            <Card.Body>
              <Card.Title style={{ color: "red" }}>
                {item.carCompany} - {item.carModel} ( {item.carColor} )
              </Card.Title>
              <Card.Text>Owner : {item.carOwner}</Card.Text>
              <Card.Text style={{ color: "green" }}>
                Charges / Day : ₹ {item.charge}
              </Card.Text>
            </Card.Body>
            <ListGroup className="list-group-flush">
              <iframe
                title="scroll360"
                data-lazy="true"
                height="300"
                width="508"
                className="youtubeVideos"
                src={item.car360}
              ></iframe>
              <ListGroup.Item>
                Reg. Date : {item.regDate} ( {item.carType} ){" "}
              </ListGroup.Item>
              <ListGroup.Item>
                Passanger Capacity : {item.passangerCapacity} Seats
              </ListGroup.Item>
              <ListGroup.Item>
                Fuel Type : {item.fuelType} ( {item.totalRunning} KM )
              </ListGroup.Item>

              <ListGroup.Item>Reg. Number : {item.numPlate}</ListGroup.Item>
              <ListGroup.Item>
                Insurance Validity : {item.insuranceValidity}
              </ListGroup.Item>
              <ListGroup.Item>Insurance : {item.insurance}</ListGroup.Item>
              <ListGroup.Item>Features : {item.feature}</ListGroup.Item>
              <ListGroup.Item>
                {/* <h5>Price : {item.price.toLocaleString("en-IN")} ₹</h5> */}
              </ListGroup.Item>
            </ListGroup>
            <Card.Body>
              <Button variant="danger" onClick={() => bookMyRide(item.id)}>
                Book Now ( {item.carCompany} - {item.carModel} )
              </Button>{" "}
            </Card.Body>
          </Card>
        ))}
      </div>
    </div>
  );
}

export default CarListing;
