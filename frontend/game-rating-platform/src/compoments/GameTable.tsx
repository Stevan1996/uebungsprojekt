import React, {useState, useEffect} from 'react';

export interface IGameData {
    id: number;
    title: string;
    releaseDate: string;
    developer: string;
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
        <table>
            <thead>
                <tr>
                    <th>Titel</th>
                    <th>Erscheinungsdatum</th>
                    <th>Entwickler</th>
                </tr>
            </thead>
            <tbody>
                {gameData(games)}
            </tbody>
        </table>
    );
}

function GameRow(props: IGameData): JSX.Element {
    return (
        <tr key={props.id.toString()}>
            <td>{props.title}</td>
            <td>{props.releaseDate}</td>
            <td>{props.developer}</td>
        </tr>
    );
}

function gameData(props: readonly IGameData[]): JSX.Element[] {
    let games: JSX.Element[] = []
    
    for(let i = 0; i < Object.keys(props).length; ++i) {
        games.push(
        <GameRow id={props[i].id}
        title={props[i].title} 
        releaseDate={props[i].releaseDate} 
        developer={props[i].developer}/>
        );
    }

    return (games);
}

export default GameTable;