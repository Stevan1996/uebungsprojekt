import React, { useState, useEffect } from "react";

export interface GameProps {
  id: number;
  title: string;
  releaseDate: string;
  developer: string[];
  avgRating: number;
  platform: string;
}

export interface GameTableProps {
  url?: string;
  platformFilter?: string;
}

export function GameTable({
  url = "/game",
  platformFilter = "Alle",
}: GameTableProps) {
  const [games, setGames] = useState<GameProps[]>([]);

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
      setGames(data);
    };

    fetchGames(url).catch(console.error);
  }, [url, platformFilter]);

  let sortedProps = games;
  if (platformFilter !== "Alle") {
    sortedProps = sortedProps.filter((prop) =>
      prop.platform.includes(platformFilter)
    );
  }
  // first sort by rating, then sort by date
  sortedProps = sortedProps.sort((obj1, obj2) => {
    if (
      obj1.avgRating > obj2.avgRating ||
      Number.isNaN(Number(obj2.avgRating))
    ) {
      return -1;
    }
    if (
      obj1.avgRating < obj2.avgRating ||
      Number.isNaN(Number(obj1.avgRating))
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
            developer={prop.developer}
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
  developer,
  releaseDate,
  title,
}: GameProps): JSX.Element {
  const ratingScore = Number.isNaN(Number(avgRating)) ? 0 : avgRating;

  return (
    <tr>
      <td>{title}</td>
      <td>{releaseDate}</td>
      <td>{developer}</td>
      <td>{ratingScore}</td>
    </tr>
  );
}

export default GameTable;
