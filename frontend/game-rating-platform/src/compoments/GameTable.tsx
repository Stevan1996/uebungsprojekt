import React, { useState, useEffect } from "react";

export interface GameProps {
  id: number;
  title: string;
  releaseDate: string;
  developers: string[];
  avgRating: number;
  platform: string;
}

export interface GameTableProps {
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

  useEffect(() => {
    const fetchGames = async (requestUrl: string) => {
      const response = await fetch(requestUrl, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      });

      let data = await response.json();
      // In case, response contains one single element
      if (data.constructor.name !== "Array") {
        data = [data];
      }
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
            id={prop.id}
            title={prop.title}
            releaseDate={prop.releaseDate}
            developers={prop.developers}
            avgRating={prop.avgRating}
            platform={prop.platform}
          />
        ))}
      </tbody>
    </table>
  );
}

function GameRow({
  id,
  avgRating,
  developers,
  releaseDate,
  title,
}: GameProps): JSX.Element {
  const ratingScore = Number.isNaN(Number(avgRating)) ? 0 : avgRating;
  const developerStr =
    developers !== undefined ? developers.join(", ") : undefined;

  return (
    <tr>
      <td>{title}</td>
      <td>{releaseDate}</td>
      <td>{developerStr}</td>
      <td>{ratingScore}</td>
    </tr>
  );
}

export default GameTable;
