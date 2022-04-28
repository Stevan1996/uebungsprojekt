import { fetchGameData, Game } from "request/gameData";
import GameDetails from "./GameDetails";
import React, { useState, useEffect, SyntheticEvent } from "react";

interface GameTableProps {
  url?: string;
  platformFilter?: string;
  searchString?: string;
}

export function GameTable({
  url = "/game",
  platformFilter = "Alle",
  searchString = "",
}: GameTableProps) {
  // cache original game data for filter operations
  const [origGameData, setOrigGameData] = useState<Game[]>([]);
  const [gameData, setGameData] = useState<Game[]>([]);
  const [activeModal, setActiveModal] = useState<boolean>(false);
  const [clickedGame, setClickedGame] = useState<Game>({
    id: 0,
    title: "",
    releaseDate: "",
    developers: [],
    description: "",
    trailer: "",
    avgRating: 0,
    platform: "",
  });

  function openModal(e: SyntheticEvent<HTMLTableRowElement, Event>) {
    setActiveModal(true);
    let filteredData = gameData.find(
      (prop) => prop.id.toString() === e.currentTarget.id
    );

    if (filteredData !== undefined) {
      setClickedGame(filteredData);
    }
  }

  function closeModal() {
    setActiveModal(false);
  }

  useEffect(() => {
    const fetchGames = async (requestUrl: string) => {
      let data: Game[];
      try {
        data = await fetchGameData(requestUrl);
      } catch (err) {
        return (
          <p className="has-text-centered">
            Spieledaten konnten nicht geladen werden.
          </p>
        );
      }
      setOrigGameData(data);
    };

    fetchGames(url).catch(console.error);
  }, [url]);

  useEffect(() => {
    setGameData(origGameData);
    if (platformFilter !== "Alle") {
      setGameData((games) =>
        games.filter((game) => game.platform.includes(platformFilter))
      );
    }
    if (searchString !== "") {
      setGameData((games) =>
        games.filter((game) =>
          game.title.toLowerCase().includes(searchString.toLowerCase())
        )
      );
    }
  }, [origGameData, platformFilter, searchString]);

  // first sort by rating, then sort by date
  const sortedGames = gameData.sort((obj1, obj2) => {
    if (obj1.avgRating > obj2.avgRating) {
      return -1;
    }
    if (obj1.avgRating < obj2.avgRating) {
      return 1;
    }

    return Date.parse(obj2.releaseDate) - Date.parse(obj1.releaseDate);
  });

  return (
    <>
      <table className="table mx-auto">
        <thead>
          <tr>
            <th>Titel</th>
            <th>Erscheinungsdatum</th>
            <th>Entwickler</th>
            <th>Bewertung</th>
          </tr>
        </thead>
        <tbody>
          {sortedGames.map<JSX.Element>((game) => (
            <GameRow game={game} onClickHandler={openModal} />
          ))}
        </tbody>
      </table>
      <GameDetails
        gameData={clickedGame}
        active={activeModal}
        closeHandler={closeModal}
      />
    </>
  );
}

interface GameRowProps {
  game: Game;
  onClickHandler: (e: SyntheticEvent<HTMLTableRowElement, Event>) => void;
}

function GameRow({ game, onClickHandler }: GameRowProps): JSX.Element {
  const developerStr =
    game.developers !== undefined ? game.developers.join(", ") : undefined;

  return (
    <tr
      onClick={onClickHandler}
      id={game.id.toString()}
      aria-haspopup="true"
      data-target="game-description-modal"
    >
      <td>{game.title}</td>
      <td>{game.releaseDate}</td>
      <td>{developerStr}</td>
      <td>{game.avgRating}</td>
    </tr>
  );
}

export default GameTable;
