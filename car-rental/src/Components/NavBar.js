import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Button from "react-bootstrap/Button";

function NavBar() {
  const [userEmail, setUserEmail] = useState("");
  const navigate = useNavigate();

  // After Signup switch to Logout
  const auth = localStorage.getItem("user");
  const logout = () => {
    localStorage.clear();
    navigate("/signup");
  };

  setTimeout(() => {
    const auth = localStorage.getItem("user");
    let data = JSON.parse(auth);
    if (data) {
      setUserEmail(data.email);
    }
  });

  const handleSubmit = (email) => {
    setTimeout(() => navigate(`/ticket-details/${email}`), 100);
  };

  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container fluid>
        <Navbar.Brand>
          <img
            style={{ width: "100px" }}
            src="https://logos.textgiraffe.com/logos/logo-name/33466763-designstyle-sunshine-l.png"
            alt="logo"
          />
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: "100px" }}
            navbarScroll
          >
            {auth ? (
              <>
                <Button
                  className="navLinkStyle"
                  onClick={() => navigate("/")}
                  variant="success"
                >
                  Home
                </Button>

                <Button
                  className="navLinkStyle"
                  onClick={logout}
                  variant="success"
                >
                  Logout ( {JSON.parse(auth).fname} )
                </Button>

                <Button
                  variant="danger"
                  onClick={() => handleSubmit(userEmail)}
                  style={{ float: "right", position: "relative", left: "280%" }}
                >
                  My Booking
                </Button>
              </>
            ) : (
              <>
                <Button
                  className="navLinkStyle"
                  onClick={() => navigate("/signup")}
                  variant="danger"
                >
                  Sign Up
                </Button>{" "}
                <Button
                  className="navLinkStyle"
                  onClick={() => navigate("/login")}
                  variant="danger"
                >
                  Login
                </Button>{" "}
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavBar;
