import React, {Component} from "react";
import "./navbar.css";

class Navbar extends Component {
    render() {
        return (
            <div className="container">
                <nav className="navbar navbar-expand-lg">
                    <div className="container-fluid">
                        <a className="navbar-brand brand-font" href="src/components/navbar/Navbar#">SpeaGroup</a>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav mlr mb-2 mb-lg-0">
                                <li className="nav-item mr-10">
                                    <a className="nav-link" aria-current="page"
                                       href="src/components/navbar/Navbar#">Problems</a>
                                </li>
                                <li className="nav-item mr-10">
                                    <a className="nav-link" href="src/components/navbar/Navbar#">Posts</a>
                                </li>
                                <li className="nav-item mr-10">
                                    <a className="nav-link" href="src/components/navbar/Navbar#" tabIndex="-1"
                                       aria-disabled="true">Questions</a>
                                </li>
                            </ul>
                            <a className="m-lg-3 button-6" role="button" href="/login">
                                Log In
                            </a>
                            <a className="button-6" role="button" href="/signup">
                                Sign Up
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
        );
    }
}

export default Navbar;