import React from 'react';

export interface IGameData {
    id: number;
    title: string;
    releaseDate: string;
    developer: string;
}

export interface IProps {
    url?: string;
}

export class GameTable extends React.Component<IProps, IGameData[]> {
    constructor(props: IProps) {
        super(props);
        this.state = [];
    }

    async componentDidMount() {
        const requestUrl = this.props.url? this.props.url : "/Games/1";
        const response = await fetch(requestUrl, {
            method: "GET",
            headers: {
              "Content-Type": "application/json"
            },
            mode: "cors"
          });
          
          let data = await response.json();
          // In case, response contains one single element
          if(data.constructor.name !== "Array"){
              data = [data]
          }
          this.setState(data);
    }

    render() {
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
                    {gameData(this.state)}
                </tbody>
            </table>
        );
    }
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