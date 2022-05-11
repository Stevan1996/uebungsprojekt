import React, { useState, SyntheticEvent } from "react";
import { getAPIData } from "request/fetchData";

export default function RegistrationForm() {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [isValid, setIsValid] = useState<boolean>(false);

  function checkInput() {
    setIsValid(email !== "" && password !== "");
  }

  function emailCallback(email: string) {
    setEmail(email);
    checkInput();
  }

  function passwordCallback(pw: string) {
    setPassword(pw);
    checkInput();
  }

  return (
    <>
      <EmailInput validate={true} emailCallback={emailCallback} />
      <PasswordInput passwordCallback={passwordCallback} />
      <div className="field">
        <div className="control">
          <button className="button is-link" disabled={!isValid}>
            Registrieren
          </button>
        </div>
      </div>
    </>
  );
}

const enum HelpStatus {
  valid = "is-success",
  invalid = "is-danger",
  neutral = "",
}

type HelpMsg = {
  msg: String;
  status: HelpStatus;
};

interface EmailInputProps {
  validate?: boolean;
  emailCallback: (mail: string) => void;
}

export function EmailInput({
  validate = false,
  emailCallback = () => {},
}: EmailInputProps) {
  const [helpMsg, setHelpMsg] = useState<HelpMsg>({
    msg: "",
    status: HelpStatus.neutral,
  });
  let iconType: string = "";

  async function validateMail(e: SyntheticEvent<HTMLInputElement, Event>) {
    if (e.currentTarget.value === "") {
      setHelpMsg({ msg: "", status: HelpStatus.neutral });
      emailCallback(e.currentTarget.value);
      return;
    }

    const emailRE = new RegExp("/[a-z0-9]+@[a-z0-9]+.[a-z]{2,3}/");
    if (emailRE.test(e.currentTarget.value.toLowerCase())) {
      setHelpMsg({ msg: "Ungültige Emailadresse", status: HelpStatus.invalid });
      return;
    }

    const emailExists: boolean = await getAPIData(
      "/user/" + e.currentTarget.value
    );
    if (emailExists) {
      setHelpMsg({
        msg: "Email bereits registriert",
        status: HelpStatus.invalid,
      });
    } else {
      setHelpMsg({ msg: "Email gültig", status: HelpStatus.valid });
      emailCallback(e.currentTarget.value);
    }

    switch (helpMsg.status) {
      case HelpStatus.invalid: {
        iconType = "fas fa-exclamation-triangle";
        break;
      }
      case HelpStatus.valid: {
        iconType = "fas fa-check";
        break;
      }
      default: {
        iconType = "";
        break;
      }
    }
  }

  const onInputHandler = validate
    ? validateMail
    : (e: SyntheticEvent<HTMLInputElement, Event>) => {
        emailCallback(e.currentTarget.value);
      };

  return (
    <div className="field">
      <label className="label">Email</label>
      <div className="control has-icons-left has-icons-right">
        <input
          className="input"
          type="email"
          placeholder="Email"
          onInput={onInputHandler}
        />
        <span className="icon is-small is-left">
          <i className="fas fa-envelope"></i>
        </span>
        <span>
          <i className={iconType}></i>
        </span>
      </div>
      <p className={"help " + helpMsg.status}>{helpMsg.msg}</p>
    </div>
  );
}

interface PasswordInputProps {
  passwordCallback: (pw: string) => void;
}
export function PasswordInput({ passwordCallback }: PasswordInputProps) {
  const [helpMsg, setHelpMsg] = useState<HelpMsg>({
    msg: "",
    status: HelpStatus.neutral,
  });

  function onChangeHandler(e: SyntheticEvent<HTMLInputElement, Event>) {
    if (e.currentTarget.value === "") {
      setHelpMsg({
        msg: "Passwort darf nicht leer sein",
        status: HelpStatus.invalid,
      });
    } else {
      setHelpMsg({ msg: "", status: HelpStatus.neutral });
      passwordCallback(e.currentTarget.value);
    }
  }

  return (
    <div className="field">
      <label className="label">Passwort</label>
      <div className="control has-icon-left">
        <input
          className="input"
          type="password"
          placeholder="Passwort"
          onChange={onChangeHandler}
        />
        <span className="icon is-small is-left">
          <i className="fas fa-lock"></i>
        </span>
      </div>
      <p className={"help " + helpMsg.status}>{helpMsg.msg}</p>
    </div>
  );
}
