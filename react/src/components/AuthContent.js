import React, { Component } from "react";
import { request } from "./axios_helper";

export default class AuthContent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    };
  }
  componentDidMount() {
    request("GET", "/messages", {})
      .then((res) => {
        this.setState({ data: res.data });
      })
      .catch((e) => {
        console.log(e);
      });
  }
  render() {
    return (
      <div className="row justify-content-md-center">
        <div className="col-4">
          <div className="card" style={{ width: "18rem" }}>
            <div className="card-body">
              <h5 className="card-title">Backend Reponse</h5>
              <p className="card-text">Content:</p>
              <ul>
              {this.state.data && this.state.data.map((msg) => <li>{msg}</li>)}
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
