import logo from './logo.svg';
import './App.css';
import Header from './components/Header';
import AppContent from './components/AppContent';

function App() {
  return (
    <div className="App">
      <Header pageTitle="Frontend authentiaction with JWT" logoSrc= {logo}/>
      <AppContent/>
    </div>
  );
}

export default App;
