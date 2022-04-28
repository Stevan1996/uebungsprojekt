import React, { useState, useEffect, SyntheticEvent } from "react";
import { fetchGameData } from "request/gameData";

interface PlatformMenuProps {
  url?: string;
  selectHandler: (e: SyntheticEvent<HTMLSelectElement, Event>) => void;
}

export default function PlatformMenu({
  url = "/game/platforms",
  selectHandler,
}: PlatformMenuProps) {
  const [platforms, setPlatforms] = useState<string[]>([]);

  useEffect(() => {
    const fetchPlatforms = async (requestUrl: string) => {
      let data: any;
      try {
        data = await fetchGameData(requestUrl);
      } catch (err) {
        return <p>Spieleplattformen konnten nicht geladen werden.</p>;
      }
      setPlatforms(data);
    };

    fetchPlatforms(url).catch(console.error);
  }, [url]);

  return (
    <p className="control">
      <span className="select">
        <select onChange={selectHandler}>
          <option value="Alle">Alle</option>
          {platforms.map((platformStr) => (
            <DropDownItem platform={platformStr} />
          ))}
        </select>
      </span>
    </p>
  );
}

interface DropDownProps {
  platform: string;
}
function DropDownItem({ platform }: DropDownProps): JSX.Element {
  return <option value={platform}>{platform}</option>;
}
