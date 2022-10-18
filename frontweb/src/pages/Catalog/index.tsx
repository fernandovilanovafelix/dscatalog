import Pagination from "components/Pagination";
import ProductCard from "components/ProductCard";
import CardLoader from "./CardLoader";
import { Product } from "types/product";
import { SpringPage } from "types/vendor/spring";
import { requestBackend } from "util/requests";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { AxiosRequestConfig } from "axios";

import "./styles.css";

const Catalog = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [page, setPage] = useState<SpringPage<Product>>();

  useEffect(() => {
    const params: AxiosRequestConfig = {
      method: "GET",
      url: "/products",
      params: {
        page: 0,
        size: 12,
      },
    };

    setIsLoading(true);
    requestBackend(params)
      .then((response) => {
        setPage(response.data);
      })
      .finally(() => {
        setIsLoading(false);
      });
  }, []);

  return (
    <div className="container my-4 catalog-container">
      <div className="row catalog-title-container">
        <h1>Catálogo de produtos</h1>
      </div>

      <div className="row">
        {isLoading ? <CardLoader /> : (
          page?.content.map((product) => (
          <div className="col-sm-6 col-lg-4 col-xl-3" key={product.id}>
            <Link to="/products/1">
              <ProductCard product={product} />
            </Link>
          </div>
        )))}
      </div>

      <div className="row">
        <Pagination />
      </div>
    </div>
  );
};

export default Catalog;