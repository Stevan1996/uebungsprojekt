export interface Game {
  id: number;
  title: string;
  releaseDate: string;
  developers: string[];
  description: string;
  trailer: string;
  avgRating: number;
  platform: string;
}

export async function fetchGameData(requestUrl: string = "/game") {
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

  return data;
}
