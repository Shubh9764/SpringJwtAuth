import React, { Component } from 'react'
import WelcomeComponent from './WelcomeComponent'
import AuthContent from './AuthContent'
import LoginForm from './LoginForm'
import { request, setAuthToken } from './axios_helper'
import { Button } from 'bootstrap'
import Buttons from './Buttons'

export default class AppContent extends Component {

    constructor(props){
        super(props)
        this.state ={
            componentToShow: "welcome"
        }
    }

     login = () => {
        this.setState({componentToShow :"login"})
    }
    logout = () => {
        this.setState({componentToShow :"welcome"})
    }
    onLogin = (e,username,password) => {
        e.preventDefault()
        request("POST","/login",{login:username,password:password})
        .then(res => {
            this.setState({componentToShow:"messages"})
            setAuthToken(res.data.token)
            console.log(res.data.token)
        })
        .catch((e) => {
            this.setState({componentToShow:"welcome"})
            console.log(e)
        })
    }
    onRegister = (e,firstName,lastName,username,password) => {
        e.preventDefault()
        request("POST","/register",{
            firstName:firstName,
            lastName:lastName,
            login:username,
            password:password})
        .then(res => {
            this.setState({componentToShow:"login"})
        })
        .catch((e) => {
            this.setState({componentToShow:"welcome"})
        })
    }

  render() {
    return (
      <div>
        <Buttons login = {this.login} logout = {this.logout}/>
        {this.state.componentToShow === "welcome" &&    <WelcomeComponent/>}
        {this.state.componentToShow === "messages" &&    <AuthContent/>}
        {this.state.componentToShow === "login" &&    <LoginForm onLogin = {this.onLogin} onRegister = {this.onRegister}/>}
      </div>
    )
  }
}
