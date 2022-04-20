import React, {useState, useEffect} from 'react';

export interface IGameData {
    id: number;
    title: string;
    releaseDate: string;
    developer: string;
    avgRating: number;
}

export interface IGameTableProps {
    url?: string;
}

export function GameTable(props: IGameTableProps) {
    const [games, setGames] = useState([])
    const requestUrl = props.url? props.url : "/game";
    
    useEffect(() => {
        const fetchGames = async(url: string) => {
            const response = await fetch(url, {
                method: "GET",
                headers: {
                  "Content-Type": "application/json"
                }
            });

            let data = await response.json();
            // In case, response contains one single element
            if(data.constructor.name !== "Array"){
                data = [data]
            }
            setGames(data);
        }
        
        fetchGames(requestUrl).catch(console.error);
    }, [requestUrl])

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
                {gameData(games)}
            </tbody>
        </table>
    );
}

function GameRow(props: IGameData): JSX.Element {
    const ratingScore = Number.isNaN(Number(props.avgRating))? 0 : props.avgRating;

    return (
        <tr>
            <td>{props.title}</td>
            <td>{props.releaseDate}</td>
            <td>{props.developer}</td>
            <td>{ratingScore}</td>
        </tr>
    );
}

function gameData(props: Array<IGameData>): JSX.Element[] {
    let gameRows: JSX.Element[] = []
    // first sort by rating, then sort by date
    const sortedProps = props.sort((obj1, obj2) => {
        if (obj1.avgRating > obj2.avgRating || Number.isNaN(Number(obj2.avgRating))) {
            return -1;
        }
        if (obj1.avgRating < obj2.avgRating || Number.isNaN(Number(obj1.avgRating))){
            return 1;
        }
        
        return Date.parse(obj2.releaseDate) - Date.parse(obj1.releaseDate);
    });

    return (
        sortedProps.map<JSX.Element>((prop) => (
            <GameRow id={prop.id}
            title={prop.title}
            releaseDate={prop.releaseDate}
            developer={prop.developer}
            avgRating={prop.avgRating}/>
        ))
    )
}

export default GameTable;