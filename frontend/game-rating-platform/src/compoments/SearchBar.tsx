import React, { useState, SyntheticEvent } from "react";
import GameTable from "./GameTable";
import PlatformMenu from "./PlatformSelector";

export default function SearchBar() {
  const [selectedPlatform, setSelectedPlatform] = useState<string>("Alle");
  const [searchQuery, setSearchQuery] = useState<string>("");

  function updatePlatformSelection(
    e: SyntheticEvent<HTMLSelectElement, Event>
  ) {
    setSelectedPlatform(e.currentTarget.value);
  }

  return (
    <>
      <div className="field has-addons has-addons-centered">
        <PlatformMenu selectHandler={updatePlatformSelection} />
        <p className="control">
          <input
            type="text"
            className="input"
            onInput={(e) => setSearchQuery(e.currentTarget.value)}
          />
        </p>
      </div>
      <GameTable platformFilter={selectedPlatform} searchString={searchQuery} />
    </>
  );
}
