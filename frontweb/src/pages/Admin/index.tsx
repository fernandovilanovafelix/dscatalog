import PrivateRoute from "components/PrivateRoute";
import Navbar from "./Navbar";
import Users from "./Users";
import Products from "./Products";

import "./styles.css";

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <PrivateRoute path="/admin/products">
          <Products />
        </PrivateRoute>
        <PrivateRoute path="/admin/categories">
          <h1>Category CRUD</h1>
        </PrivateRoute>
        <PrivateRoute path="/admin/users" roles={["ROLE_ADMIN"]}>
          <Users />
        </PrivateRoute>
      </div>
    </div>
  );
};

export default Admin;
