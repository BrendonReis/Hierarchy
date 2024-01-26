import { Footer } from "flowbite-react";
import "./footer.css";
function Component() {
  return (
    <Footer className="footer">
      <div className="footer-container">
        <Footer.Copyright href="#" by="Brendon Campos" />
        <Footer.LinkGroup className="footer-link-group">
          <Footer.Link href="#">Desenvolvedor Full Stack</Footer.Link>
        </Footer.LinkGroup>
      </div>
    </Footer>
  );
}

export default Component;
