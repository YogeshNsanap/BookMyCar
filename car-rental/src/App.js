import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import NavBar from "./Components/NavBar";
import PrivateComp from "./Components/PrivateComp";
import CarListing from "./Components/CarListing";
import SignUp from "./Components/SignUp";
import Login from "./Components/Login";
import Booking from "./Components/Booking";
import Driver from "./Components/Driver";
import ATMcard from "./Components/ATM";
import MyOrder from "./Components/MyOrder";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route element={<PrivateComp />}>
            <Route path="/" element={<CarListing />} />
            <Route path="/book-car/:id" element={<Booking />} />
            <Route path="/driver" element={<Driver />} />
            <Route path="ATM" element={<ATMcard />} />
            <Route path="ticket-details/:email" element={<MyOrder />} />
          </Route>
          <Route path="/signup" element={<SignUp />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
