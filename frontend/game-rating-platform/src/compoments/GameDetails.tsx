import React from "react";
import { Game } from "request/fetchData";

interface GameDetailsProps {
  gameData: Game;
  active: boolean;
  closeHandler: () => void;
}

export default function GameDetails({
  gameData,
  active = false,
  closeHandler,
}: GameDetailsProps): JSX.Element {
  return (
    <>
      <div
        id="game-description-modal"
        className={"modal" + (active ? "is-active" : "")}
      >
        <div className="modal-background" onClick={closeHandler}></div>
        <div className="modal-card">
          <header className="modal-card-head">
            <p className="modal-card-title">{gameData.title}</p>
            <button
              className="delete"
              aria-label="close"
              onClick={closeHandler}
            ></button>
          </header>
          <section className="modal-card-body">
            <p>Erscheinungsdatum: {gameData.releaseDate}</p>
            <p>Entwickler: {gameData.developers.join(", ")}</p>
            <p>Bewertung: {gameData.avgRating}</p>
            <p>
              <a href={gameData.trailer}>Trailer</a>
            </p>
            <h1 className="title is-3">Beschreibung</h1>
            <p>{gameData.description}</p>
          </section>
        </div>
      </div>
    </>
  );
}
