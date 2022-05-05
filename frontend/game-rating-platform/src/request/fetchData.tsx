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

export async function getAPIData(requestUrl: string) {
  const response = await fetch(requestUrl, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });

  return response.json();
}

export async function fetchGameData(requestUrl: string = "/game") {
  let data = await getAPIData(requestUrl);
  // In case, response contains one single element
  if (data.constructor.name !== "Array") {
    data = [data];
  }

  return data;
}

export async function postData(requestUrl: string, data = {}) {
  const header = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  };

  const response = await fetch(requestUrl, header);
  return response.json();
}
