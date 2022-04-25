import { fetchGameData, GameProps } from "request/gameData";
import GameDetails from "./DescriptionPopUp";
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
  const [origGameData, setOrigGameData] = useState<GameProps[]>([]);
  const [gameData, setGameData] = useState<GameProps[]>([]);
  const [activeModal, setActiveModal] = useState<boolean>(false);
  const [clickedGame, setClickedGame] = useState<GameProps>({
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
    let filteredData = gameData.filter(
      (prop) => prop.title === e.currentTarget.id
    );
    console.log(filteredData);
    if (filteredData.length !== 0) {
      setClickedGame(filteredData[0]);
    }
  }

  function closeModal() {
    setActiveModal(false);
  }

  useEffect(() => {
    const fetchGames = async (requestUrl: string) => {
      let data: GameProps[] = await fetchGameData(requestUrl);
      data = data.map((props) => {
        props.avgRating = Number.isNaN(Number(props.avgRating))
          ? 0
          : props.avgRating;
        return props;
      });
      setOrigGameData(data);
    };

    fetchGames(url).catch(console.error);
  }, [url]);

  useEffect(() => {
    setGameData(origGameData);
    if (platformFilter !== "Alle") {
      setGameData((games) =>
        games.filter((prop) => prop.platform.includes(platformFilter))
      );
    }
    if (searchString !== "") {
      setGameData((games) =>
        games.filter((prop) =>
          prop.title.toLowerCase().includes(searchString.toLowerCase())
        )
      );
    }
  }, [origGameData, platformFilter, searchString]);

  // first sort by rating, then sort by date
  const sortedProps = gameData.sort((obj1, obj2) => {
    if (
      obj1.avgRating > obj2.avgRating ||
      (Number.isNaN(Number(obj2.avgRating)) &&
        !Number.isNaN(Number(obj1.avgRating)))
    ) {
      return -1;
    }
    if (
      obj1.avgRating < obj2.avgRating ||
      (Number.isNaN(Number(obj1.avgRating)) &&
        !Number.isNaN(Number(obj2.avgRating)))
    ) {
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
          {sortedProps.map<JSX.Element>((prop) => (
            <GameRow
              title={prop.title}
              releaseDate={prop.releaseDate}
              developers={prop.developers}
              avgRating={prop.avgRating}
              onClickHandler={openModal}
            />
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
  avgRating: number;
  developers: string[];
  releaseDate: string;
  title: string;
  onClickHandler: (e: SyntheticEvent<HTMLTableRowElement, Event>) => void;
}

function GameRow({
  avgRating,
  developers,
  releaseDate,
  title,
  onClickHandler,
}: GameRowProps): JSX.Element {
  const developerStr =
    developers !== undefined ? developers.join(", ") : undefined;

  return (
    <tr
      onClick={onClickHandler}
      id={title}
      aria-haspopup="true"
      data-target="game-description-modal"
    >
      <td>{title}</td>
      <td>{releaseDate}</td>
      <td>{developerStr}</td>
      <td>{avgRating}</td>
    </tr>
  );
}

export default GameTable;
