import Login from "./components/Login";
import { describe, test } from "vitest";
import { render, screen } from "@testing-library/react";
import { AlertProvider } from "./AlertContext";

describe("AlertContext test", () => {
  beforeEach(() => {
    const { container } = render(
      <AlertProvider>
        <Login></Login>
      </AlertProvider>
    );
  });

  test("Should show placeholder", () => {
    expect(screen.getByPlaceholderText(/Alert Originator/i)).toBeDefined();
  });

  // test("Should not show if not logged in", () => {
  //   const { container } = render(
  //     <AlertProvider>
  //       <Login></Login>
  //     </AlertProvider>
  //   );
  //   expect(container.getElementsByTagName("h4")).toBe(false);
  // });
});
