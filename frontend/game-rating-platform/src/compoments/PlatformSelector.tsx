import React, { useState, useEffect, SyntheticEvent } from "react";
import GameTable from "./GameTable";

interface PlatformProps {
  id: number;
  platform: string;
}

interface PlatformMenuProps {
  url?: string;
}

export default function PlatformMenu({
  url = "/platforms",
}: PlatformMenuProps) {
  const [platforms, setPlatforms] = useState<PlatformProps[]>([]);
  const [buttonText, setButtonText] = useState<string>("Alle");

  useEffect(() => {
    const fetchPlatforms = async (requestUrl: string) => {
      const response = await fetch(requestUrl, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });

      let platformData = await response.json();
      setPlatforms(platformData);
    };

    fetchPlatforms(url).catch(console.error);
  });

  function updateButtonText(e: SyntheticEvent<HTMLAnchorElement, Event>) {
    setButtonText(e.currentTarget.text);
  }

  return (
    <>
      <div className="dropdown is-active">
        <div className="dropdown-trigger">
          <button
            className="button"
            aria-haspopup="true"
            aria-controls="dropdown-menu"
          >
            <span>{buttonText}</span>
            <span className="icon is-small">
              <i className="fas fa-angle-down" aria-hidden="true" />
            </span>
          </button>
        </div>
        <div className="dropdown-menu" id="dropdown-menu" role="menu">
          <div className="dropdown-content">
            {platforms.map<JSX.Element>((prop) => (
              <DropDownItem
                platform={prop.platform}
                selectHandler={updateButtonText}
              />
            ))}
          </div>
        </div>
      </div>
      <GameTable platformFilter={buttonText} />
    </>
  );
}

interface DropDownProps {
  platform: string;
  selectHandler: (arg: SyntheticEvent<HTMLAnchorElement, Event>) => void;
  withDivider?: boolean;
}
function DropDownItem({
  platform,
  selectHandler,
  withDivider = false,
}: DropDownProps): JSX.Element {
  let divider: JSX.Element = <></>;
  if (withDivider) {
    divider = <hr className="dropdown-divider" />;
  }

  return (
    <>
      {divider}
      <a href="#" className="dropdown-item" onSelect={selectHandler}>
        {platform}
      </a>
    </>
  );
}
