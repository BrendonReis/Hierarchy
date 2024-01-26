// Home.js

import Dropfile from "../components/login/login";
import Footer from "../components/footer/footer";

const Home = () => {
  const handleFileUpload = (fileContent) => {
  };
  return (
    <>
      <Dropfile onFileUpload={handleFileUpload} />
      <Footer />
    </>
  );
};

export default Home;
