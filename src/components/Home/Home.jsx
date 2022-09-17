import React, {Component} from "react";
import front_img from "./static/img/undraw_questions_re_1fy7.svg";
import "./Home.css";

class Home extends Component {
    render() {
        return (
            <div className="container mar-top-10">
                <div className="row">
                    <div className="col-lg-6">
                        <img className="img-style" src={front_img} alt=""/>
                    </div>
                    <div className="col-lg-6">
                        <div className="front-card">
                            <h1>Welcome to SpeaGroup</h1>
                            <p className="roboto-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Impedit, incidunt magni nemo quas
                                recusandae soluta. Aliquam aliquid commodi consequatur cupiditate debitis dicta doloremque,
                                ducimus ea eos esse eum explicabo illum inventore itaque magnam neque numquam officia
                                officiis pariatur placeat rem tempore, tenetur totam ullam velit veniam vitae voluptatem!
                                Dolorum, quibusdam.</p>
                            <button className="cssbuttons-io-button"> Get started
                                <div className="icon">
                                    <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M0 0h24v24H0z" fill="none"></path>
                                        <path
                                            d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"
                                            fill="currentColor"></path>
                                    </svg>
                                </div>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Home;